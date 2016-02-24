package util;

import com.example.q97531x.myapplication.R;

/**
 * Created by q97531x on 2015/8/12.
 */
public class TypeData {
    int[] image = new int[]{
        R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher,R.drawable.ic_launcher, R.drawable.ic_launcher,R.drawable.ic_launcher
    };
    String[] text = new String[]{
            "餐饮","购物","交通","娱乐","居家","医药","进修","人情","投资","其他"
    };
    public int getImage(int i){
        return image[i];
    }
    public void setImage(int position,int id){
        image[position] = id;
    }
    public int getImageSize(){
        return image.length;
    }
    public String getText(int i){
        return text[i];
    }
    public void setText(int position,String type){
        text[position] = type;
    }
    public int getTextSize(){
        return text.length;
    }
}
