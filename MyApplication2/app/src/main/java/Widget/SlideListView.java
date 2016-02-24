package Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Scroller;


/**
 * Created by q97531x on 16/2/24.
 */
public class SlideListView extends ListView{
    private int ScreenWidth;
    private Scroller scroller;
    private static final int SNAP_VELOCITY = 600;
    //认为是用户滑动的最小滑动距离
    private int minTouchSloop;
    private RemoveListener removeListener;
    private RemoveDirection removeDirection;
    //手指按下的位置
    private int downX;
    private int downY;
    private int slidePosition;
    private View itemView;
    private boolean isSlide = false;
    /**
     * 速度追踪对象
     */
    private VelocityTracker velocityTracker;
    public enum RemoveDirection{
        LEFT,RIGHT
    }
    /**
     *
     * 当ListView item滑出屏幕，回调这个接口
     * 我们需要在回调方法removeItem()中移除该Item,然后刷新ListView
     *
     * @author xiaanming
     *
     */
    public interface RemoveListener {
        public void removeItem(RemoveDirection direction, int position);
    }
    public SlideListView(Context context) {
        this(context, null);
    }

    public SlideListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取屏幕宽度
        ScreenWidth = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        //初始化滑动类
        scroller = new Scroller(context);
        minTouchSloop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }
    //设置滑动删除的回调接口
    public void SetRemoveListener(RemoveListener removeListener){
        this.removeListener = removeListener;
    }
    //事件分发

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                addVelocityTracker(ev);
                //如果滚动未结束,直接返回
                if(!scroller.isFinished()){
                    return super.dispatchTouchEvent(ev);
                }
                downX = (int)ev.getX();
                downY = (int)ev.getY();
                slidePosition = pointToPosition(downX,downY);
                if(slidePosition == AdapterView.INVALID_POSITION){
                    return super.dispatchTouchEvent(ev);
                }
                //获取点击的itemview
                itemView = getChildAt(slidePosition - getFirstVisiblePosition());
                break;
            case MotionEvent.ACTION_MOVE:
                if(Math.abs(getScrollVelocity())> SNAP_VELOCITY||(Math.abs(ev.getX() - downX) > minTouchSloop && Math.abs(ev.getY() - downY) < minTouchSloop)){
                isSlide = true;
            }
                break;
            case MotionEvent.ACTION_UP:
                recycleVelocityTracker();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(isSlide&&slidePosition!=AdapterView.INVALID_POSITION){
            //父类截获消息
            requestDisallowInterceptTouchEvent(true);
            addVelocityTracker(ev);
            final int action = ev.getAction();
            int x = (int)ev.getX();
            switch (action){
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    MotionEvent cancelEvent = MotionEvent.obtain(ev);
                    cancelEvent.setAction(MotionEvent.ACTION_CANCEL | (ev.getActionIndex() << MotionEvent.ACTION_POINTER_INDEX_SHIFT));
                    onTouchEvent(cancelEvent);
                    int deltaX = downX - x;
                    downX = x;
                    //item滑动,deltaX>0向左,否则向右
                    itemView.scrollBy(deltaX,0);
                    return true;  //拖动的时候ListView不滚动
                case MotionEvent.ACTION_UP:
                    int velocityX = getScrollVelocity();
                    if(velocityX > SNAP_VELOCITY){
                        scrollRight();
                    }else if(velocityX < -SNAP_VELOCITY){
                        scrollLeft();
                    }else {
                        scrollByDistanceX();
                    }

                    recycleVelocityTracker();
                    isSlide = false;

                    break;
            }
        }

        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        // 调用startScroll的时候scroller.computeScrollOffset()返回true，
        if (scroller.computeScrollOffset()) {
            // 让ListView item根据当前的滚动偏移量进行滚动
            itemView.scrollTo(scroller.getCurrX(), scroller.getCurrY());

            postInvalidate();

            // 滚动动画结束的时候调用回调接口
            if (scroller.isFinished()) {
                if (removeListener == null) {
                    throw new NullPointerException("RemoveListener is null, we should called setRemoveListener()");
                }

                itemView.scrollTo(0, 0);
                removeListener.removeItem(removeDirection, slidePosition);
            }
        }
    }

    public void scrollRight(){
        removeDirection = RemoveDirection.LEFT;
        //偏移量
        final int delta = (ScreenWidth - itemView.getScrollX());
        scroller.startScroll(itemView.getScrollX(),0,delta,0,Math.abs(delta));
        postInvalidate();
    }
    public void scrollLeft(){
        removeDirection = RemoveDirection.RIGHT;
        //偏移量
        final int delta = (ScreenWidth - itemView.getScrollX());
        scroller.startScroll(itemView.getScrollX(),0,delta,0,Math.abs(delta));
        postInvalidate();
    }
    /**
     * 根据手指滚动itemView的距离来判断是滚动到开始位置还是向左或者向右滚动
     */
    private void scrollByDistanceX() {
        // 如果向左滚动的距离大于屏幕的二分之一，就让其删除
        if (itemView.getScrollX() >= ScreenWidth / 2) {
            scrollLeft();
        } else if (itemView.getScrollX() <= -ScreenWidth / 2) {
            scrollRight();
        } else {
            // 滚回到原始位置,为了偷下懒这里是直接调用scrollTo滚动
            itemView.scrollTo(0, 0);
        }

    }
    /**
     * 添加用户的速度跟踪器
     *
     * @param event
     */
    private void addVelocityTracker(MotionEvent event) {
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }

        velocityTracker.addMovement(event);
    }

    /**
     * 移除用户速度跟踪器
     */
    private void recycleVelocityTracker() {
        if (velocityTracker != null) {
            velocityTracker.recycle();
            velocityTracker = null;
        }
    }
    /**
     * 获取X方向的滑动速度,大于0向右滑动，反之向左
     *
     * @return
     */
    private int getScrollVelocity() {
        velocityTracker.computeCurrentVelocity(1000);
        int velocity = (int) velocityTracker.getXVelocity();
        return velocity;
    }
}
