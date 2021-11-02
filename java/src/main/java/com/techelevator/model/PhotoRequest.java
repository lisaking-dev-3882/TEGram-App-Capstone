package com.techelevator.model;
public class PhotoRequest {

   private Integer userId;
   private String fileName;
   private String link; 
   private String caption;
   public Integer getUserId() {
      return userId;
   }
   public void setUserId(Integer userId) {
      this.userId = userId;
   }
   public String getFileName() {
      return fileName;
   }
   public void setFileName(String fileName) {
      this.fileName = fileName;
   }
   public String getLink() {
      return link;
   }
   public void setLink(String link) {
      this.link = link;
   }
   public String getCaption() {
      return caption;
   }
   public void setCaption(String caption) {
      this.caption = caption;
   }

}
