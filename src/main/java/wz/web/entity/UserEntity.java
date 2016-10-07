package wz.web.entity;

/**
 * Created by zhuowang on 16/9/27.
 */
public class UserEntity {
    private String name;
    private int age;
    private int sex;
    private boolean daniu;

    public UserEntity(String name, int age, int sex, boolean daniu) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.daniu = daniu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public boolean isDaniu() {
        return daniu;
    }

    public void setDaniu(boolean daniu) {
        this.daniu = daniu;
    }
}
