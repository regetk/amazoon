package slet;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author reget.kalamees
 */
@XmlRootElement(name = "Items")
public class FoundItems {
    @XmlElement(name = "Item", type = FoundItem.class)
    private List<FoundItem> fi = new ArrayList<FoundItem>();

	public FoundItems() {}
        
        public FoundItems(List<FoundItem> fis) {
		this.fi = fis;
	}

    /**
     * @return the fItems
     */
    public List<FoundItem> getfItems() {
        return fi;
    }

    /**
     * @param fItems the fItems to set
     */
    public void setfItems(List<FoundItem> fItems) {
        this.fi = fItems;
    }
        
    
}
