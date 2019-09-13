package org.openrqm.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * RQMElement
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-12T19:15:09.451Z")

public class RQMElement {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("document_id")
  private Long documentId = null;

  @JsonProperty("element_type_id")
  private Long elementTypeId = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("rank")
  private String rank = null;

  @JsonProperty("parent_element_id")
  private Long parentElementId = null;

  public RQMElement id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * 
   * @return id
   **/
  @ApiModelProperty(value = "")

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public RQMElement documentId(Long documentId) {
    this.documentId = documentId;
    return this;
  }

  /**
   * Get documentId
   * 
   * @return documentId
   **/
  @ApiModelProperty(value = "")

  public Long getDocumentId() {
    return documentId;
  }

  public void setDocumentId(Long documentId) {
    this.documentId = documentId;
  }

  public RQMElement elementTypeId(Long elementTypeId) {
    this.elementTypeId = elementTypeId;
    return this;
  }

  /**
   * Get elementTypeId
   * 
   * @return elementTypeId
   **/
  @ApiModelProperty(value = "")

  public Long getElementTypeId() {
    return elementTypeId;
  }

  public void setElementTypeId(Long elementTypeId) {
    this.elementTypeId = elementTypeId;
  }

  public RQMElement content(String content) {
    this.content = content;
    return this;
  }

  /**
   * Get content
   * 
   * @return content
   **/
  @ApiModelProperty(value = "")

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public RQMElement rank(String rank) {
    this.rank = rank;
    return this;
  }

  /**
   * Get rank
   * 
   * @return rank
   **/
  @ApiModelProperty(value = "")

  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }

  public RQMElement parentElementId(Long parentElementId) {
    this.parentElementId = parentElementId;
    return this;
  }

  /**
   * Get parentElementId
   * 
   * @return parentElementId
   **/
  @ApiModelProperty(value = "")

  public Long getParentElementId() {
    return parentElementId;
  }

  public void setParentElementId(Long parentElementId) {
    this.parentElementId = parentElementId;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RQMElement rqMElement = (RQMElement) o;
    return Objects.equals(this.id, rqMElement.id) && Objects.equals(this.documentId, rqMElement.documentId)
        && Objects.equals(this.elementTypeId, rqMElement.elementTypeId)
        && Objects.equals(this.content, rqMElement.content) && Objects.equals(this.rank, rqMElement.rank)
        && Objects.equals(this.parentElementId, rqMElement.parentElementId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, documentId, elementTypeId, content, rank, parentElementId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RQMElement {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    documentId: ").append(toIndentedString(documentId)).append("\n");
    sb.append("    elementTypeId: ").append(toIndentedString(elementTypeId)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    rank: ").append(toIndentedString(rank)).append("\n");
    sb.append("    parentElementId: ").append(toIndentedString(parentElementId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
