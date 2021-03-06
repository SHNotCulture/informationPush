package com.eparking.informationPush.dao;

import com.eparking.informationPush.dao.sqlProvider.ParkRouteConfSqlProvider;
import com.eparking.informationPush.entity.system.ParkRouteConf;
import com.eparking.informationPush.entity.system.ParkRouteConfCriteria;
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

public interface ParkRouteConfMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    @SelectProvider(type= ParkRouteConfSqlProvider.class, method="countByExample")
    long countByExample(ParkRouteConfCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    @DeleteProvider(type=ParkRouteConfSqlProvider.class, method="deleteByExample")
    int deleteByExample(ParkRouteConfCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    @Delete({
        "delete from park_route_conf",
        "where park_id = #{parkId,jdbcType=INTEGER}",
          "and route_id = #{routeId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(@Param("parkId") Integer parkId, @Param("routeId") Integer routeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    @Insert({
        "insert into park_route_conf (park_id, route_id, ",
        "route_park_id, route_park_name, ",
        "in_park_port, out_park_port, ",
        "xkzh, public_key)",
        "values (#{parkId,jdbcType=INTEGER}, #{routeId,jdbcType=INTEGER}, ",
        "#{routeParkId,jdbcType=VARCHAR}, #{routeParkName,jdbcType=VARCHAR}, ",
        "#{inParkPort,jdbcType=VARCHAR}, #{outParkPort,jdbcType=VARCHAR}, ",
        "#{xkzh,jdbcType=VARCHAR}, #{publicKey,jdbcType=VARCHAR})"
    })
    int insert(ParkRouteConf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    @InsertProvider(type=ParkRouteConfSqlProvider.class, method="insertSelective")
    int insertSelective(ParkRouteConf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    @SelectProvider(type=ParkRouteConfSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="route_id", property="routeId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="route_park_id", property="routeParkId", jdbcType=JdbcType.VARCHAR),
        @Result(column="route_park_name", property="routeParkName", jdbcType=JdbcType.VARCHAR),
        @Result(column="in_park_port", property="inParkPort", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_park_port", property="outParkPort", jdbcType=JdbcType.VARCHAR),
        @Result(column="xkzh", property="xkzh", jdbcType=JdbcType.VARCHAR),
        @Result(column="public_key", property="publicKey", jdbcType=JdbcType.VARCHAR)
    })
    List<ParkRouteConf> selectByExample(ParkRouteConfCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "park_id, route_id, route_park_id, route_park_name, in_park_port, out_park_port, ",
        "xkzh, public_key",
        "from park_route_conf",
        "where park_id = #{parkId,jdbcType=INTEGER}",
          "and route_id = #{routeId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="route_id", property="routeId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="route_park_id", property="routeParkId", jdbcType=JdbcType.VARCHAR),
        @Result(column="route_park_name", property="routeParkName", jdbcType=JdbcType.VARCHAR),
        @Result(column="in_park_port", property="inParkPort", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_park_port", property="outParkPort", jdbcType=JdbcType.VARCHAR),
        @Result(column="xkzh", property="xkzh", jdbcType=JdbcType.VARCHAR),
        @Result(column="public_key", property="publicKey", jdbcType=JdbcType.VARCHAR)
    })
    ParkRouteConf selectByPrimaryKey(@Param("parkId") Integer parkId, @Param("routeId") Integer routeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    @UpdateProvider(type=ParkRouteConfSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ParkRouteConf record, @Param("example") ParkRouteConfCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    @UpdateProvider(type=ParkRouteConfSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ParkRouteConf record, @Param("example") ParkRouteConfCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    @UpdateProvider(type=ParkRouteConfSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ParkRouteConf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    @Update({
        "update park_route_conf",
        "set route_park_id = #{routeParkId,jdbcType=VARCHAR},",
          "route_park_name = #{routeParkName,jdbcType=VARCHAR},",
          "in_park_port = #{inParkPort,jdbcType=VARCHAR},",
          "out_park_port = #{outParkPort,jdbcType=VARCHAR},",
          "xkzh = #{xkzh,jdbcType=VARCHAR},",
          "public_key = #{publicKey,jdbcType=VARCHAR}",
        "where park_id = #{parkId,jdbcType=INTEGER}",
          "and route_id = #{routeId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ParkRouteConf record);
}