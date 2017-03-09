//3-9-17
//Donald - Kristine
//getting data on a specific item from the walmart open api
public class GetItem {
	static String QOPEN = "http://api.walmartlabs.com/v1/items1/";
	static String QCLOSE = "?apiKey={apiKey}&lsPublisherId={Your LinkShare Publisher Id}&format=xml";
	
	public void GetItem(){
		//initializes the class
	}
	public String getQuery(int x){
		String result ="";
		result = QOPEN+x+QCLOSE;
		return result;
	}
	public String readResult(String x){
		String[][] raw = new String[100][2];//the data received from walmart before it is sorted
		String[][] parsed = new String[9][2];//the data after it is sorted
		parsed[0][0]="date";//contains the date last modified
		parsed[1][0]="name";//contains the date last modified
		parsed[2][0]="salePrice";//contains the date last modified
		parsed[3][0]="date";//contains the date last modified
		parsed[4][0]="date";//contains the date last modified
		parsed[5][0]="date";//contains the date last modified
		parsed[6][0]="date";//contains the date last modified
		parsed[7][0]="date";//contains the date last modified
		parsed[8][0]="date";//contains the date last modified	
		
		String name;
		double price;
		int quantity;
		String image;
		boolean sale;
		String description;
		String notes;
		int itemid;
		/*
		 * {  
   "items":[  
      {  
         "itemId":50285046,
         "parentItemId":50285046,
         "name":"Straight Talk Apple iPhone 5S 16GB 4G LTE Prepaid Smartphone",
         "msrp":450,
         "salePrice":149,
         "upc":"190198051875",
         "categoryPath":"Cell Phones/Straight Talk Wireless/Straight Talk Cell Phones",
         "longDescription":"Straight Talk Apple iPhone 5S 16GB 4G LTE Prepaid Smartphone:4&quot; Retina displayA7 chip with M7 motion coprocessorTouch ID fingerprint sensorNew 8MP iSight camera with True Tone flash1080p HD video recordingFaceTime HD cameraUltrafast 4G LTE wirelessOver 900,000 apps on the App StoreiOS 7 ; the world's most advanced mobile OSiCloud ; your content on all your devicesResolution: 1136 x 640Storage: 16GBBluetooth 4.0 wireless technologyWireless data: 802.11a/b/g/n WiFi (802.11n 2.4GHz and 5GHz)Assisted GPS with GLONASSBuilt-in rechargeable lithium-ion batteryTalk time: Up to 10 hours on 3GStandby time: Up to 250 hoursInternet use: Up to 8 hours on 3G, up to 10 hours on LTE, up to 10 hours on WiFiVideo playback: Up to 10 hoursAudio playback: Up to 40 hoursWeight and dimensions:Height: 4.87&quot;Width: 2.31&quot;Depth: 0.30&quot;Weight: 3.95 ozWhat's in the Box:Apple iPhone 5SApple EarPods with remote and micLightning to USB CableUSB Power AdapterDocumentation",
         "brandName":"Apple",
         "thumbnailImage":"https://i5.walmartimages.com/asr/20caa881-9f84-478b-8259-b9c3448e1007_1.0872b05362d97ff68033417572228b40.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF",
         "mediumImage":"https://i5.walmartimages.com/asr/20caa881-9f84-478b-8259-b9c3448e1007_1.0872b05362d97ff68033417572228b40.jpeg?odnHeight=180&odnWidth=180&odnBg=FFFFFF",
         "largeImage":"https://i5.walmartimages.com/asr/20caa881-9f84-478b-8259-b9c3448e1007_1.0872b05362d97ff68033417572228b40.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF",
         "productTrackingUrl":"http://linksynergy.walmart.com/fs-bin/click?id=|LSNID|&offerid=223073.7200&type=14&catid=8&subid=0&hid=7200&tmpid=1082&RD_PARM1=http%253A%252F%252Fwww.walmart.com%252Fip%252FStraight-Talk-Apple-iPhone-5S-16GB-4G-LTE-Prepaid-Smartphone%252F50285046%253Faffp1%253DOsmgDBzME0u1DW9mDF71IG7sllmBeqZpgYsrALOGfjc%2526affilsrc%253Dapi",
         "ninetySevenCentShipping":false,
         "standardShipRate":0,
         "color":"Gray",
         "marketplace":false,
         "shipToStore":true,
         "freeShipToStore":true,
         "modelNumber":"iPhone 5S",
         "productUrl":"http://c.affil.walmart.com/t/api01?l=http%3A%2F%2Fwww.walmart.com%2Fip%2FStraight-Talk-Apple-iPhone-5S-16GB-4G-LTE-Prepaid-Smartphone%2F50285046%3Faffp1%3DOsmgDBzME0u1DW9mDF71IG7sllmBeqZpgYsrALOGfjc%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi",
         "customerRating":"4.289",
         "customerRatingImage":"http://i2.walmartimages.com/i/CustRating/4_3.gif",
         "categoryNode":"1105910_1045119_1068804",
         "rollBack":true,
         "bundle":false,
         "clearance":false,
         "stock":"Available",
         "attributes":{"actualColor":"Space Gray"},
         "addToCartUrl":"http://c.affil.walmart.com/t/api01?l=http%3A%2F%2Faffil.walmart.com%2Fcart%2FaddToCart%3Fitems%3D50285046%7C1%26affp1%3DOsmgDBzME0u1DW9mDF71IG7sllmBeqZpgYsrALOGfjc%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi",
         "affiliateAddToCartUrl":"http://linksynergy.walmart.com/fs-bin/click?id=|LSNID|&offerid=223073.7200&type=14&catid=8&subid=0&hid=7200&tmpid=1082&RD_PARM1=http%253A%252F%252Faffil.walmart.com%252Fcart%252FaddToCart%253Fitems%253D50285046%257C1%2526affp1%253DOsmgDBzME0u1DW9mDF71IG7sllmBeqZpgYsrALOGfjc%2526affilsrc%253Dapi",
         "freeShippingOver50Dollars":true,
         "giftOptions":{  "allowGiftWrap":false,
         "allowGiftMessage":true,
         "allowGiftReceipt":false},
         "imageEntities":[  {  "thumbnailImage":"https://i5.walmartimages.com/asr/c7447d99-c90c-4039-b098-7ec0949e85ea_1.d6bf6d15f16849b61e85deeac75035c7.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF",
         "mediumImage":"https://i5.walmartimages.com/asr/c7447d99-c90c-4039-b098-7ec0949e85ea_1.d6bf6d15f16849b61e85deeac75035c7.jpeg?odnHeight=180&odnWidth=180&odnBg=FFFFFF",
         "largeImage":"https://i5.walmartimages.com/asr/c7447d99-c90c-4039-b098-7ec0949e85ea_1.d6bf6d15f16849b61e85deeac75035c7.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF",
         "entityType":"SECONDARY"},
            {  
         "thumbnailImage":"https://i5.walmartimages.com/asr/20caa881-9f84-478b-8259-b9c3448e1007_1.0872b05362d97ff68033417572228b40.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF",
         "mediumImage":"https://i5.walmartimages.com/asr/20caa881-9f84-478b-8259-b9c3448e1007_1.0872b05362d97ff68033417572228b40.jpeg?odnHeight=180&odnWidth=180&odnBg=FFFFFF",
         "largeImage":"https://i5.walmartimages.com/asr/20caa881-9f84-478b-8259-b9c3448e1007_1.0872b05362d97ff68033417572228b40.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF",
         "entityType":"PRIMARY"}],
         "offerType":"ONLINE_AND_STORE",
         "shippingPassEligible":true,
         "availableOnline":true
      }
   ]
}
		 * */

		
		
		
	/*TODO*/	return null;
		
	}
	private String SIF(String name, double price, int quantity, String image, boolean sale, String description, String notes,int itemid){
		String result = "";
		String temp = "";
		//^^^^^^^^^^^^^^^ variables
		//vvvvvvvvvvvvvvv SIF
		result += "SIF:";
		//^^^^^^^^^^^^^^^ SIF
		//vvvvvvvvvvvvvvv name		
		result+= ":";
		result += name;
		result +=":";
		//^^^^^^^^^^^^^^^ name
		//vvvvvvvvvvvvvvv price	
		result+= ":";
		temp = String.valueOf(price);
		result += temp;
		temp = "";
		result+= ":";
		//^^^^^^^^^^^^^^^ price
		//vvvvvvvvvvvvvvv quantity
		result+= ":";
		temp = String.valueOf(quantity);
		result += temp;
		temp ="";
		result+= ":";
		//^^^^^^^^^^^^^^^ quantity
		//vvvvvvvvvvvvvvv image	
		result+= ":";
		result += image;
		result+= ":";		
		//^^^^^^^^^^^^^^^ image
		//vvvvvvvvvvvvvvv sale	
		result+= ":";
		temp = String.valueOf(sale);
		result += temp;
		temp = "";
		result+= ":";		
		//^^^^^^^^^^^^^^^ sale
		//vvvvvvvvvvvvvvv itemid
		result+= ":";
		temp = String.valueOf(itemid);
		result += temp;
		temp = "";
		result+= ":";
		//^^^^^^^^^^^^^^^ itemid	
		//vvvvvvvvvvvvvvv description
		result+= ":";
		result += description;
		result+= ":";		
		//^^^^^^^^^^^^^^^ description
		//vvvvvvvvvvvvvvv notes
		result+= ":";
		result += notes;
		result+= ":";			
		

	return result;
	}
	
	
	
	
	
	
	

}
