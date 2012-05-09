package pl.mkubala.cashflow.model.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.wicket.IClusterable;

@Entity
public class Bill implements IClusterable, Comparable<Bill> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private final BigDecimal amount;

    private final Date createDate;

    @Column(length = 4096)
    private String description;

    public Bill() {
        this.amount = BigDecimal.ZERO;
        this.createDate = new Date();
    };

    public Bill(final BigDecimal amount, final Date createDate, final String description) {
        this.amount = amount;
        this.createDate = createDate;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(1, 31).append(id).append(createDate).append(amount).append(description).build();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bill other = (Bill) obj;
        return new EqualsBuilder().append(getId(), other.getId()).append(getCreateDate(), other.getCreateDate())
                .append(getAmount(), other.getAmount()).append(getDescription(), other.getDescription()).build();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Bill [");
        sb.append("id=");
        sb.append(id);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", amount=");
        sb.append(amount);
        sb.append(", description=");
        sb.append(description);
        sb.append(']');
        return sb.toString();
    }

    @Override
    public int compareTo(final Bill other) {
        return new CompareToBuilder().append(getCreateDate(), other.getCreateDate()).append(getAmount(), other.getAmount())
                .append(getDescription(), other.getDescription()).append(getId(), other.getId()).build();
    }

}
