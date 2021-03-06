package com.eparking.informationPush.dao;

import com.eparking.informationPush.dao.sqlProvider.RouteInfoSqlProvider;
import com.eparking.informationPush.entity.system.RouteInfo;
import com.eparking.informationPush.entity.system.RouteInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface RouteInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table route_info
     *
     * @mbg.generated
     */
    @SelectProvider(type= RouteInfoSqlProvider.class, method="countByExample")
    long countByExample(RouteInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table route_info
     *
     * @mbg.generated
     */
    @DeleteProvider(type=RouteInfoSqlProvider.class, method="deleteByExample")
    int deleteByExample(RouteInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table route_info
     *
     * @mbg.generated
     */
    @Delete({
        "delete from route_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table route_info
     *
     * @mbg.generated
     */
    @Insert({
        "insert into route_info (id, name, ",
        "username, password, ",
        "ver_num, server_ip, ",
        "server_port, in_path, ",
        "out_path, heart_path, ",
        "static_path, dynamic_path)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{verNum,jdbcType=VARCHAR}, #{serverIp,jdbcType=VARCHAR}, ",
        "#{serverPort,jdbcType=VARCHAR}, #{inPath,jdbcType=VARCHAR}, ",
        "#{outPath,jdbcType=VARCHAR}, #{heartPath,jdbcType=VARCHAR}, ",
        "#{staticPath,jdbcType=VARCHAR}, #{dynamicPath,jdbcType=VARCHAR})"
    })
    int insert(RouteInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table route_info
     *
     * @mbg.generated
     */
    @InsertProvider(type=RouteInfoSqlProvider.class, method="insertSelective")
    int insertSelective(RouteInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table route_info
     *
     * @mbg.generated
     */
    @SelectProvider(type=RouteInfoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="ver_num", property="verNum", jdbcType=JdbcType.VARCHAR),
        @Result(column="server_ip", property="serverIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="server_port", property="serverPort", jdbcType=JdbcType.VARCHAR),
        @Result(column="in_path", property="inPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_path", property="outPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="heart_path", property="heartPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="static_path", property="staticPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="dynamic_path", property="dynamicPath", jdbcType=JdbcType.VARCHAR)
    })
    List<RouteInfo> selectByExample(RouteInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table route_info
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, name, username, password, ver_num, server_ip, server_port, in_path, out_path, ",
        "heart_path, static_path, dynamic_path",
        "from route_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="ver_num", property="verNum", jdbcType=JdbcType.VARCHAR),
        @Result(column="server_ip", property="serverIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="server_port", property="serverPort", jdbcType=JdbcType.VARCHAR),
        @Result(column="in_path", property="inPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_path", property="outPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="heart_path", property="heartPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="static_path", property="staticPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="dynamic_path", property="dynamicPath", jdbcType=JdbcType.VARCHAR)
    })
    RouteInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table route_info
     *
     * @mbg.generated
     */
    @UpdateProvider(type=RouteInfoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") RouteInfo record, @Param("example") RouteInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table route_info
     *
     * @mbg.generated
     */
    @UpdateProvider(type=RouteInfoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") RouteInfo record, @Param("example") RouteInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table route_info
     *
     * @mbg.generated
     */
    @UpdateProvider(type=RouteInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RouteInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table route_info
     *
     * @mbg.generated
     */
    @Update({
        "update route_info",
        "set name = #{name,jdbcType=VARCHAR},",
          "username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "ver_num = #{verNum,jdbcType=VARCHAR},",
          "server_ip = #{serverIp,jdbcType=VARCHAR},",
          "server_port = #{serverPort,jdbcType=VARCHAR},",
          "in_path = #{inPath,jdbcType=VARCHAR},",
          "out_path = #{outPath,jdbcType=VARCHAR},",
          "heart_path = #{heartPath,jdbcType=VARCHAR},",
          "static_path = #{staticPath,jdbcType=VARCHAR},",
          "dynamic_path = #{dynamicPath,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RouteInfo record);
}