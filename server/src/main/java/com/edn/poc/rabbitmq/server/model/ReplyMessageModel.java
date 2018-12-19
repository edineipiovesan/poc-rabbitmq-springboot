package com.edn.poc.rabbitmq.server.model;

public class ReplyMessageModel {

    private String id;
    private String name;
    private Long number;

    @java.beans.ConstructorProperties({"id", "name", "number"})
    public ReplyMessageModel(String id, String name, Long number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public ReplyMessageModel() {

    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Long getNumber() {
        return this.number;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ReplyMessageModel)) return false;
        final ReplyMessageModel other = (ReplyMessageModel) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$number = this.getNumber();
        final Object other$number = other.getNumber();
        if (this$number == null ? other$number != null : !this$number.equals(other$number)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ReplyMessageModel;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $number = this.getNumber();
        result = result * PRIME + ($number == null ? 43 : $number.hashCode());
        return result;
    }

    public String toString() {
        return "ReplyMessageModel(id=" + this.getId() + ", name=" + this.getName() + ", number=" + this.getNumber() + ")";
    }
}
