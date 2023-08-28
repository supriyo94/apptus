package Apptus.util;

import java.util.List;

public class ResponseDTO {
    String docxUrl;
    long deleteCount;
    long insertCount;
    int totalChangeCount;
    List<String> deletedText;
    List<String> insertedText;

    public String getDocxUrl() {
        return docxUrl;
    }

    public long getDeleteCount() {
        return deleteCount;
    }

    public long getInsertCount() {
        return insertCount;
    }

    public int getTotalChangeCount() {
        return totalChangeCount;
    }

    public List<String> getDeletedText() {
        return deletedText;
    }

    public List<String> getInsertedText() {
        return insertedText;
    }

    public void setDocxUrl(String docxUrl) {
        this.docxUrl = docxUrl;
    }

    public void setDeleteCount(long deleteCount) {
        this.deleteCount = deleteCount;
    }

    public void setInsertCount(long insertCount) {
        this.insertCount = insertCount;
    }

    public void setTotalChangeCount(int totalChangeCount) {
        this.totalChangeCount = totalChangeCount;
    }

    public void setDeletedText(List<String> deletedText) {
        this.deletedText = deletedText;
    }

    public void setInsertedText(List<String> insertedText) {
        this.insertedText = insertedText;
    }



}
