package com.xxgc.dialect;
import com.xxgc.utils.DictUtils;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

import java.util.Collections;
import java.util.Set;

/**
 * @Description: 自定义方言
 */
public class DictDialect extends AbstractDialect implements IExpressionObjectDialect {
    public DictDialect(String name) {
        super(name);
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return new IExpressionObjectFactory() {
            //定义自定获取的前缀名称
            @Override
            public Set<String> getAllExpressionObjectNames() {
                return Collections.singleton("dict");
            }

            //将字典工具类注册
            @Override
            public Object buildObject(IExpressionContext iExpressionContext, String s) {
                return new DictUtils();
            }

            @Override
            public boolean isCacheable(String s) {
                return true;
            }
        };
    }
}