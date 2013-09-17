package slet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author reget.kalamees
 */

@XmlRootElement(name="Item")
public class FoundItem {
    private String itemId;
    private FoundItem.ItemAttributes itemAttr;
    private FoundItem.OfferSummary offerSummary;
   

    /**
     * @return the itemId
     */
    @XmlElement(name="ASIN")
    public String getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * @return the itemAttr
     */
    public FoundItem.ItemAttributes getItemAttr() {
        return itemAttr;
    }

    /**
     * @param itemAttr the itemAttr to set
     */
    @XmlElement(name="ItemAttributes")
    public void setItemAttr(FoundItem.ItemAttributes itemAttr) {
        this.itemAttr = itemAttr;
    }

   

    /**
     * @return the offerSummary
     */
    public FoundItem.OfferSummary getOfferSummary() {
        return offerSummary;
    }

    /**
     * @param offerSummary the offerSummary to set
     */
    @XmlElement(name="OfferSummary")
    public void setOfferSummary(FoundItem.OfferSummary offerSummary) {
        this.offerSummary = offerSummary;
    }
   public static class ItemAttributes{
        private String itemTitle;

        /**
         * @return the itemTitle
         */
        public String getItemTitle() {
            return itemTitle;
        }

        /**
         * @param itemTitle the itemTitle to set
         */
        @XmlElement(name="Title")
        public void setItemTitle(String itemTitle) {
            this.itemTitle = itemTitle;
        }
       
   
   
   }
   public static class OfferSummary{
       private OfferSummary.LowestNewPrice lowestNewPrice;

        /**
         * @return the lowestNewPrice
         */
        public OfferSummary.LowestNewPrice getLowestNewPrice() {
            return lowestNewPrice;
        }

        /**
         * @param lowestNewPrice the lowestNewPrice to set
         */
        @XmlElement(name="LowestNewPrice")
        public void setLowestNewPrice(OfferSummary.LowestNewPrice lowestNewPrice) {
            this.lowestNewPrice = lowestNewPrice;
        }
       
       public static class LowestNewPrice{
           private String amount;
           private String currency;

            /**
             * @return the amount
             */
            public String getAmount() {
                return amount;
            }

            /**
             * @param amount the amount to set
             */
            @XmlElement(name="Amount")
            public void setAmount(String amount) {
                this.amount = amount;
            }

            /**
             * @return the currency
             */
            public String getCurrency() {
                return currency;
            }

            /**
             * @param currency the currency to set
             */
            @XmlElement(name="CurrencyCode")
            public void setCurrency(String currency) {
                this.currency = currency;
            }
       
       
       
       }
   
   }
    
    
}
