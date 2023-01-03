package com.xtpeach.tiny.basics.common.module.core;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * ID 主键编码
 * column: id
 * excel: id(1)
 *
 * @author xtpeach
 */
@Data
@MappedSuperclass
public abstract class ID implements Serializable {

    /**
     * 指定id生成方式
     *
     * @GenericGenerator GenericGenerator注解是hibernate所提供的自定义主键生成策略生成器由GenericGenerator实现多定义的策略
     * 所以，它要配合GeneratedValue一起使用，GeneratedValue注解中的”generator”属性要与，
     * GenericGenerator注解中name属性一致，strategy属性表示hibernate的主键生成策略
     * <p>
     * 主键生成策略一共有13种
     * 1.GENERATORS.put("uuid", UUIDHexGenerator.class);
     * 采用128位的uuid算法生成主键，uuid被编码为一个32位16进制数字的字符串。占用空间大（字符串类型）
     * @GeneratedValue(generator = "paymentableGenerator")
     * @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
     * <p>
     * 2.GENERATORS.put("hilo", TableHiLoGenerator.class);
     * hilo 要在数据库中建立一张额外的表，默认表名为hibernate_unque_key，默认字段为integer类型，名称是next_hi（比较少用）
     * @GeneratedValue(generator = "paymentableGenerator")
     * @GenericGenerator(name = "paymentableGenerator", strategy = "hilo")
     * <p>
     * 3.GENERATORS.put("assigned", Assigned.class);
     * assigned 在插入数据的时候主键由程序处理（很常用），这是<generator>元素没有指定时的默认生成策略。等同于JPA中的AUTO。
     * @GeneratedValue(generator = "paymentableGenerator")
     * @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
     * <p>
     * 4.GENERATORS.put("identity", IdentityGenerator.class);
     * identity 使用SQL Server和MySQL的自增字段，这个方法不能放到Oracle中，Oracle不支持自增字段，要设定sequence(MySQL和SQL Server中很常用)。等同于JPA中的IDENTITY
     * @GeneratedValue(generator = "paymentableGenerator")
     * @GenericGenerator(name = "paymentableGenerator", strategy = "identity")
     * <p>
     * 5.GENERATORS.put("select", SelectGenerator.class);
     * select 使用触发器生成主键（主要用于早期的数据库主键生成机制，少用）
     * @GeneratedValue(generator = "paymentableGenerator")
     * @GenericGenerator(name="select", strategy="select",
     * parameters = { @Parameter(name = "key", value = "idstoerung") })
     * <p>
     * 6.GENERATORS.put("sequence", SequenceGenerator.class);
     * sequence 调用谨慎数据库的序列来生成主键，要设定序列名，不然hibernate无法找到。
     * @GeneratedValue(generator = "paymentableGenerator")
     * @GenericGenerator(name = "paymentableGenerator", strategy = "sequence",
     * parameters = { @Parameter(name = "sequence", value = "seq_payablemoney") })
     * <p>
     * 7.GENERATORS.put("seqhilo", SequenceHiLoGenerator.class);
     * seqhilo 通过hilo算法实现，但是主键历史保存在Sequence中，适用于支持Sequence的数据库，如Orcale(比较少用）
     * @GeneratedValue(generator = "paymentableGenerator")
     * @GenericGenerator(name = "paymentableGenerator", strategy = "seqhilo",
     * parameters = { @Parameter(name = "max_lo", value = "5") })
     * <p>
     * 8.GENERATORS.put("increment", IncrementGenerator.class);
     * increment 插入数据的时候hibernate会给主键添加一个自增的主键，但是一个hibernate实例就维护一个计数器，所以在多个实例运行的时候不能使用这个方法。
     * @GeneratedValue(generator = "paymentableGenerator")
     * @GenericGenerator(name = "paymentableGenerator", strategy = "increment")
     * <p>
     * 9.GENERATORS.put("foreign", ForeignGenerator.class);
     * @GeneratedValue(generator = "idGenerator")
     * @GenericGenerator(name = "idGenerator", strategy = "foreign",
     * parameters = { @Parameter(name = "property", value = "employee") })
     * <p>
     * 10.GENERATORS.put("guid", GUIDGenerator.class);
     * guid 采用数据库底层的guid算法机制，对应MySQL的uuid()函数，SQL Server的newid()函数，ORCALE的rawtohex(sys_guid())函数等
     * @GeneratedValue(generator = "paymentableGenerator")
     * @GenericGenerator(name = "paymentableGenerator", strategy = "guid")
     * <p>
     * 11.GENERATORS.put("uuid.hex", UUIDHexGenerator.class); //uuid.hex is deprecated
     * uuid.hex 看uudi,建议用uuid替换
     * @GeneratedValue(generator = "paymentableGenerator")
     * @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
     * <p>
     * 12.GENERATORS.put("sequence-identity", SequenceIdentityGenerator.class);
     * @GeneratedValue(generator = "paymentableGenerator")
     * @GenericGenerator(name = "paymentableGenerator", strategy = "sequence-identity",
     * parameters = { @Parameter(name = "sequence", value = "seq_payablemoney") })
     * <p>
     * 13. native
     * native对于orcale采用Sequence方式，对于MySQL和SQL Server采用identity(处境主键生成机制)，native就是将主键的生成工作交给数据库本身，hibernate不管。（很常用）
     * @GeneratedValue(generator = "paymentableGenerator")
     * @GenericGenerator(name = "paymentableGenerator", strategy = "native")
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "com.xtpeach.tiny.basics.common.module.core.IdGenerator")
    @Column(unique = true, nullable = false, updatable = false, length = 32)
    @TableId(type = IdType.ASSIGN_UUID)
    @Excel(name = "id", orderNum = "0")
    private String id;

}