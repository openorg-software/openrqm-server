package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RQMDocument
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-12T19:15:09.451Z")

public class RQMDocument   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("workspace_id")
  private Long workspaceId = null;

  @JsonProperty("internal_identifier")
  private Long internalIdentifier = null;

  @JsonProperty("external_identifier")
  private Long externalIdentifier = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("short_name")
  private String shortName = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("confidentiality")
  private String confidentiality = null;

  @JsonProperty("author_id")
  private Long authorId = null;

  @JsonProperty("language_id")
  private Long languageId = null;

  @JsonProperty("approver_id")
  private Long approverId = null;

  @JsonProperty("reviewer_text")
  private String reviewerText = null;

  @JsonProperty("last_modified_by_id")
  private Long lastModifiedById = null;

  @JsonProperty("last_modified_on")
  private OffsetDateTime lastModifiedOn = null;

  @JsonProperty("baseline_major")
  private Long baselineMajor = null;

  @JsonProperty("baseline_minor")
  private Long baselineMinor = null;

  @JsonProperty("baseline_review")
  private Long baselineReview = null;

  @JsonProperty("previous_baseline_id")
  private Long previousBaselineId = null;

  public RQMDocument id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public RQMDocument workspaceId(Long workspaceId) {
    this.workspaceId = workspaceId;
    return this;
  }

  /**
   * Get workspaceId
   * @return workspaceId
  **/
  @ApiModelProperty(value = "")


  public Long getWorkspaceId() {
    return workspaceId;
  }

  public void setWorkspaceId(Long workspaceId) {
    this.workspaceId = workspaceId;
  }

  public RQMDocument internalIdentifier(Long internalIdentifier) {
    this.internalIdentifier = internalIdentifier;
    return this;
  }

  /**
   * Get internalIdentifier
   * @return internalIdentifier
  **/
  @ApiModelProperty(value = "")


  public Long getInternalIdentifier() {
    return internalIdentifier;
  }

  public void setInternalIdentifier(Long internalIdentifier) {
    this.internalIdentifier = internalIdentifier;
  }

  public RQMDocument externalIdentifier(Long externalIdentifier) {
    this.externalIdentifier = externalIdentifier;
    return this;
  }

  /**
   * Get externalIdentifier
   * @return externalIdentifier
  **/
  @ApiModelProperty(value = "")


  public Long getExternalIdentifier() {
    return externalIdentifier;
  }

  public void setExternalIdentifier(Long externalIdentifier) {
    this.externalIdentifier = externalIdentifier;
  }

  public RQMDocument name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RQMDocument shortName(String shortName) {
    this.shortName = shortName;
    return this;
  }

  /**
   * Get shortName
   * @return shortName
  **/
  @ApiModelProperty(value = "")


  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public RQMDocument description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public RQMDocument confidentiality(String confidentiality) {
    this.confidentiality = confidentiality;
    return this;
  }

  /**
   * Get confidentiality
   * @return confidentiality
  **/
  @ApiModelProperty(value = "")


  public String getConfidentiality() {
    return confidentiality;
  }

  public void setConfidentiality(String confidentiality) {
    this.confidentiality = confidentiality;
  }

  public RQMDocument authorId(Long authorId) {
    this.authorId = authorId;
    return this;
  }

  /**
   * Get authorId
   * @return authorId
  **/
  @ApiModelProperty(value = "")


  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

  public RQMDocument languageId(Long languageId) {
    this.languageId = languageId;
    return this;
  }

  /**
   * Get languageId
   * @return languageId
  **/
  @ApiModelProperty(value = "")


  public Long getLanguageId() {
    return languageId;
  }

  public void setLanguageId(Long languageId) {
    this.languageId = languageId;
  }

  public RQMDocument approverId(Long approverId) {
    this.approverId = approverId;
    return this;
  }

  /**
   * Get approverId
   * @return approverId
  **/
  @ApiModelProperty(value = "")


  public Long getApproverId() {
    return approverId;
  }

  public void setApproverId(Long approverId) {
    this.approverId = approverId;
  }

  public RQMDocument reviewerText(String reviewerText) {
    this.reviewerText = reviewerText;
    return this;
  }

  /**
   * Get reviewerText
   * @return reviewerText
  **/
  @ApiModelProperty(value = "")


  public String getReviewerText() {
    return reviewerText;
  }

  public void setReviewerText(String reviewerText) {
    this.reviewerText = reviewerText;
  }

  public RQMDocument lastModifiedById(Long lastModifiedById) {
    this.lastModifiedById = lastModifiedById;
    return this;
  }

  /**
   * Get lastModifiedById
   * @return lastModifiedById
  **/
  @ApiModelProperty(value = "")


  public Long getLastModifiedById() {
    return lastModifiedById;
  }

  public void setLastModifiedById(Long lastModifiedById) {
    this.lastModifiedById = lastModifiedById;
  }

  public RQMDocument lastModifiedOn(OffsetDateTime lastModifiedOn) {
    this.lastModifiedOn = lastModifiedOn;
    return this;
  }

  /**
   * Get lastModifiedOn
   * @return lastModifiedOn
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getLastModifiedOn() {
    return lastModifiedOn;
  }

  public void setLastModifiedOn(OffsetDateTime lastModifiedOn) {
    this.lastModifiedOn = lastModifiedOn;
  }

  public RQMDocument baselineMajor(Long baselineMajor) {
    this.baselineMajor = baselineMajor;
    return this;
  }

  /**
   * Get baselineMajor
   * @return baselineMajor
  **/
  @ApiModelProperty(value = "")


  public Long getBaselineMajor() {
    return baselineMajor;
  }

  public void setBaselineMajor(Long baselineMajor) {
    this.baselineMajor = baselineMajor;
  }

  public RQMDocument baselineMinor(Long baselineMinor) {
    this.baselineMinor = baselineMinor;
    return this;
  }

  /**
   * Get baselineMinor
   * @return baselineMinor
  **/
  @ApiModelProperty(value = "")


  public Long getBaselineMinor() {
    return baselineMinor;
  }

  public void setBaselineMinor(Long baselineMinor) {
    this.baselineMinor = baselineMinor;
  }

  public RQMDocument baselineReview(Long baselineReview) {
    this.baselineReview = baselineReview;
    return this;
  }

  /**
   * Get baselineReview
   * @return baselineReview
  **/
  @ApiModelProperty(value = "")


  public Long getBaselineReview() {
    return baselineReview;
  }

  public void setBaselineReview(Long baselineReview) {
    this.baselineReview = baselineReview;
  }

  public RQMDocument previousBaselineId(Long previousBaselineId) {
    this.previousBaselineId = previousBaselineId;
    return this;
  }

  /**
   * Get previousBaselineId
   * @return previousBaselineId
  **/
  @ApiModelProperty(value = "")


  public Long getPreviousBaselineId() {
    return previousBaselineId;
  }

  public void setPreviousBaselineId(Long previousBaselineId) {
    this.previousBaselineId = previousBaselineId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RQMDocument rqMDocument = (RQMDocument) o;
    return Objects.equals(this.id, rqMDocument.id) &&
        Objects.equals(this.workspaceId, rqMDocument.workspaceId) &&
        Objects.equals(this.internalIdentifier, rqMDocument.internalIdentifier) &&
        Objects.equals(this.externalIdentifier, rqMDocument.externalIdentifier) &&
        Objects.equals(this.name, rqMDocument.name) &&
        Objects.equals(this.shortName, rqMDocument.shortName) &&
        Objects.equals(this.description, rqMDocument.description) &&
        Objects.equals(this.confidentiality, rqMDocument.confidentiality) &&
        Objects.equals(this.authorId, rqMDocument.authorId) &&
        Objects.equals(this.languageId, rqMDocument.languageId) &&
        Objects.equals(this.approverId, rqMDocument.approverId) &&
        Objects.equals(this.reviewerText, rqMDocument.reviewerText) &&
        Objects.equals(this.lastModifiedById, rqMDocument.lastModifiedById) &&
        Objects.equals(this.lastModifiedOn, rqMDocument.lastModifiedOn) &&
        Objects.equals(this.baselineMajor, rqMDocument.baselineMajor) &&
        Objects.equals(this.baselineMinor, rqMDocument.baselineMinor) &&
        Objects.equals(this.baselineReview, rqMDocument.baselineReview) &&
        Objects.equals(this.previousBaselineId, rqMDocument.previousBaselineId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, workspaceId, internalIdentifier, externalIdentifier, name, shortName, description, confidentiality, authorId, languageId, approverId, reviewerText, lastModifiedById, lastModifiedOn, baselineMajor, baselineMinor, baselineReview, previousBaselineId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RQMDocument {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    workspaceId: ").append(toIndentedString(workspaceId)).append("\n");
    sb.append("    internalIdentifier: ").append(toIndentedString(internalIdentifier)).append("\n");
    sb.append("    externalIdentifier: ").append(toIndentedString(externalIdentifier)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    shortName: ").append(toIndentedString(shortName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    confidentiality: ").append(toIndentedString(confidentiality)).append("\n");
    sb.append("    authorId: ").append(toIndentedString(authorId)).append("\n");
    sb.append("    languageId: ").append(toIndentedString(languageId)).append("\n");
    sb.append("    approverId: ").append(toIndentedString(approverId)).append("\n");
    sb.append("    reviewerText: ").append(toIndentedString(reviewerText)).append("\n");
    sb.append("    lastModifiedById: ").append(toIndentedString(lastModifiedById)).append("\n");
    sb.append("    lastModifiedOn: ").append(toIndentedString(lastModifiedOn)).append("\n");
    sb.append("    baselineMajor: ").append(toIndentedString(baselineMajor)).append("\n");
    sb.append("    baselineMinor: ").append(toIndentedString(baselineMinor)).append("\n");
    sb.append("    baselineReview: ").append(toIndentedString(baselineReview)).append("\n");
    sb.append("    previousBaselineId: ").append(toIndentedString(previousBaselineId)).append("\n");
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

