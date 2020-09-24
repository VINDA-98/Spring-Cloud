package top.weidaboy.springcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Dept implements Serializable {
    private long deptno;
    private String dname;

    //查看数据表来自哪个数据库,使用函数DATABASE();
    //微服务中可以一个服务对应一个数据库,同一个信息可能存在不同数据库中
    private String db_source;

    /**
     * 链式编程dept. setDpetNo(11).setDname( 'SSSS ').setDb_ source();
     */
}
