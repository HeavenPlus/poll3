package com.briup.apps.poll.bean;

public class Course {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column poll_course.id
     *
     * @mbg.generated Mon Jun 25 16:00:03 CST 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column poll_course.name
     *
     * @mbg.generated Mon Jun 25 16:00:03 CST 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column poll_course.period
     *
     * @mbg.generated Mon Jun 25 16:00:03 CST 2018
     */
    private String period;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column poll_course.description
     *
     * @mbg.generated Mon Jun 25 16:00:03 CST 2018
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column poll_course.id
     *
     * @return the value of poll_course.id
     *
     * @mbg.generated Mon Jun 25 16:00:03 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column poll_course.id
     *
     * @param id the value for poll_course.id
     *
     * @mbg.generated Mon Jun 25 16:00:03 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column poll_course.name
     *
     * @return the value of poll_course.name
     *
     * @mbg.generated Mon Jun 25 16:00:03 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column poll_course.name
     *
     * @param name the value for poll_course.name
     *
     * @mbg.generated Mon Jun 25 16:00:03 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column poll_course.period
     *
     * @return the value of poll_course.period
     *
     * @mbg.generated Mon Jun 25 16:00:03 CST 2018
     */
    public String getPeriod() {
        return period;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column poll_course.period
     *
     * @param period the value for poll_course.period
     *
     * @mbg.generated Mon Jun 25 16:00:03 CST 2018
     */
    public void setPeriod(String period) {
        this.period = period == null ? null : period.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column poll_course.description
     *
     * @return the value of poll_course.description
     *
     * @mbg.generated Mon Jun 25 16:00:03 CST 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column poll_course.description
     *
     * @param description the value for poll_course.description
     *
     * @mbg.generated Mon Jun 25 16:00:03 CST 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}