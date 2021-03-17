package za.co.ashtech.trek.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Trail
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-14T13:33:11.730Z[GMT]")


public class Trail   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("length")
  private String length = null;

  @JsonProperty("level")
  private String level = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("status")
  private String status = null;

  public Trail name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(example = "The Pipe Track", required = true, description = "")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Trail location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
   **/
  @Schema(example = "Table Mountain", required = true, description = "")
      @NotNull

    public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Trail length(String length) {
    this.length = length;
    return this;
  }

  /**
   * Get length
   * @return length
   **/
  @Schema(example = "5", required = true, description = "")
      @NotNull

    public String getLength() {
    return length;
  }

  public void setLength(String length) {
    this.length = length;
  }

  public Trail level(String level) {
    this.level = level;
    return this;
  }

  /**
   * Get level
   * @return level
   **/
  @Schema(example = "Beginner", required = true, description = "")
      @NotNull

    public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public Trail description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @Schema(example = "From leisurely rambles to invigorating hikes, Cape Town has something for everyone.", required = true, description = "")
      @NotNull

    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Trail status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   **/
  @Schema(example = "O", required = true, description = "")
      @NotNull

    public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Trail trail = (Trail) o;
    return Objects.equals(this.name, trail.name) &&
        Objects.equals(this.location, trail.location) &&
        Objects.equals(this.length, trail.length) &&
        Objects.equals(this.level, trail.level) &&
        Objects.equals(this.description, trail.description) &&
        Objects.equals(this.status, trail.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, location, length, level, description, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Trail {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    length: ").append(toIndentedString(length)).append("\n");
    sb.append("    level: ").append(toIndentedString(level)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
