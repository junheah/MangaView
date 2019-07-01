package mangaview;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import okhttp3.Response;

public class UpdatedList {
    public UpdatedList(){
        //
    }
    public void fetch(CustomHttpClient client){
        result = new ArrayList<>();
        String url = "/bbs/board.php?bo_table=manga&page=";
        if(!last) {
            try {
                page++;
                Response response= client.get(url+page);
                Document document = Jsoup.parse(response.body().string());
                Elements items = document.select("div.post-row");
                if (items.size() < 50) last = true;
                for(Element item : items){
                    String ttmp = item.selectFirst("div.img-item").selectFirst("img").attr("src");
                    String ntmp = item.selectFirst("div.post-subject").selectFirst("a").ownText().replace('\n',' ');
                    String idStr = item.selectFirst("div.post-info").selectFirst("a").attr("href").split("manga_id=")[1];
                    int id = Integer.parseInt(idStr);

                    String idRaw = item.selectFirst("div.post-image").selectFirst("a.ellipsis").attr("href");
                    int itmp = Integer.parseInt(idRaw.substring(idRaw.lastIndexOf("=")+1));

                    String dtmp = item.selectFirst("div.post-info").selectFirst("span").ownText();

                    Manga tmp = new Manga(0,itmp,ntmp,dtmp);
                    tmp.setTitle(new Title("","","",new ArrayList<String>(), -1, id));
                    tmp.addThumb(ttmp);
                    result.add(tmp);
                }
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
                page--;
            }
        }
    }

    public ArrayList<Manga> getResult() {
        return result;
    }
    public boolean isLast(){return last;}

    
    public void firstPage() {
    	this.page =0;
    }
    Boolean last = false;
    ArrayList<Manga> result;
    int page = 0;
}
