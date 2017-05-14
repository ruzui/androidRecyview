package text.bwie.com.androidrecyview.bean;
import java.io.Serializable;
/**
 * Created by dell on 2017/5/13.
 */
    public class JavaBean implements Serializable{
        private String title;
         private String images;
        private boolean check;

    public JavaBean(String title, String images, boolean check) {
        this.title = title;
        this.images = images;
        this.check = check;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
