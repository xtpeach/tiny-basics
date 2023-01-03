package com.xtpeach.tiny.basics.common.module.core;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 自定义ID生成器
 *
 * @author xymar
 * @create 2022/11/4
 */
@Component
public class IdGenerator extends UUIDGenerator implements IdentifierGenerator {

    /**
     * hibernate
     * 生成id，当对象有id值时不生成
     *
     * @param session
     * @param o
     * @return
     * @throws HibernateException
     */
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object o) throws HibernateException {
        Object id = getId(o);
        if (id != null) {
            return (Serializable) id;
        }
        return UUIDStrGenerator.generate();
    }

    /**
     * mybatis-plus
     * 生成id，当对象有id值时不生成
     * @param o
     * @return
     */
    @Override
    public String nextUUID(Object o) {
        Object id = getId(o);
        if (id != null) {
            return (String) id;
        }
        return UUIDStrGenerator.generate();
    }

    /**
     * mybatis-plus
     * 暂不使用
     *
     * @param o
     * @return
     */
    @Override
    public Number nextId(Object o) {
        return null;
    }

    /**
     * 获取对象id值
     *
     * @param o
     * @return
     */
    private Object getId(Object o) {
        Method method = null;
        try {
            method = o.getClass().getMethod("getId");
            return method.invoke(o);
        } catch (Exception e) {
            return method;
        }
    }

}
