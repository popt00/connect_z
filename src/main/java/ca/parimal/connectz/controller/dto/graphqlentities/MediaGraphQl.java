package ca.parimal.connectz.controller.dto.graphqlentities;
import org.json.simple.JSONObject;

public class MediaGraphQl {
    public static final String QUERY="media{id, title {romaji,english}}";
    public MediaGraphQl (JSONObject obj){
        setMediaId(Integer.parseInt(obj.get("id").toString()));
        JSONObject title =(JSONObject)obj.get("title");
        if(title.get("english")!=null){
            setTitle(title.get("english").toString());
        }
        else{
            setTitle(title.get("romaji").toString());
        }
    }

    private Integer mediaId;
    private String title;

    public MediaGraphQl(Integer mediaId, String title) {
        this.mediaId = mediaId;
        this.title = title;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
