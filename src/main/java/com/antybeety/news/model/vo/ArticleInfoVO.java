package com.antybeety.news.model.vo;
import lombok.Data;

@Data
public class ArticleInfoVO {
	private String code;
	private String title;
	private String pressName;
	private String summary;
	private String articleTime;
	private int viewCnt;
	private String url;
	private String imgURL;
	private String districtName;
	private String keywordName;
	
	public ArticleInfoVO() {
		
	}
	public ArticleInfoVO(String code, String title, String pressName, String summary, String articleTime,
						 int viewCnt, String url, String imgURL, String districtName, String keywordName) {
		this.code=code;
		this.title=title;
		this.pressName=pressName;
		this.summary=summary;
		this.articleTime=articleTime;
		this.viewCnt=viewCnt;
		this.url = url;
		this.imgURL=imgURL;
		this.districtName=districtName;
		this.keywordName=keywordName;
	}

	public String toString() {
		return "ArticleInfo [code=" + code + ", title=" + title + ", pressName=" + pressName + ", summary=" + summary
				+ ", articleTime=" + articleTime + ", viewCnt=" + viewCnt + ", URL=" + url + ", imgURL=" + imgURL
				+ ", districtName=" + districtName + ", keywordName=" + keywordName + "]";
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((articleTime == null) ? 0 : articleTime.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((districtName == null) ? 0 : districtName.hashCode());
		result = prime * result + ((imgURL == null) ? 0 : imgURL.hashCode());
		result = prime * result + ((keywordName == null) ? 0 : keywordName.hashCode());
		result = prime * result + ((pressName == null) ? 0 : pressName.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + viewCnt;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleInfoVO other = (ArticleInfoVO) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (articleTime == null) {
			if (other.articleTime != null)
				return false;
		} else if (!articleTime.equals(other.articleTime))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (districtName == null) {
			if (other.districtName != null)
				return false;
		} else if (!districtName.equals(other.districtName))
			return false;
		if (imgURL == null) {
			if (other.imgURL != null)
				return false;
		} else if (!imgURL.equals(other.imgURL))
			return false;
		if (keywordName == null) {
			if (other.keywordName != null)
				return false;
		} else if (!keywordName.equals(other.keywordName))
			return false;
		if (pressName == null) {
			if (other.pressName != null)
				return false;
		} else if (!pressName.equals(other.pressName))
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (viewCnt != other.viewCnt)
			return false;
		return true;
	}
	
}
