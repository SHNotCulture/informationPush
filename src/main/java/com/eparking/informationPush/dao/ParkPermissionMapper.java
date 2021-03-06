package com.eparking.informationPush.dao;

import com.eparking.informationPush.dao.sqlProvider.ParkPermissionSqlProvider;
import com.eparking.informationPush.entity.system.ParkPermission;
import com.eparking.informationPush.entity.system.ParkPermissionCriteria;
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

public interface ParkPermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_permission
     *
     * @mbg.generated
     */
    @SelectProvider(type= ParkPermissionSqlProvider.class, method="countByExample")
    long countByExample(ParkPermissionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_permission
     *
     * @mbg.generated
     */
    @DeleteProvider(type=ParkPermissionSqlProvider.class, method="deleteByExample")
    int deleteByExample(ParkPermissionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_permission
     *
     * @mbg.generated
     */
    @Delete({
        "delete from park_permission",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_permission
     *
     * @mbg.generated
     */
    @Insert({
        "insert into park_permission (id, park_name, ",
        "status, route_ids)",
        "values (#{id,jdbcType=INTEGER}, #{parkName,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=INTEGER}, #{routeIds,jdbcType=VARCHAR})"
    })
    int insert(ParkPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_permission
     *
     * @mbg.generated
     */
    @InsertProvider(type=ParkPermissionSqlProvider.class, method="insertSelective")
    int insertSelective(ParkPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_permission
     *
     * @mbg.generated
     */
    @SelectProvider(type=ParkPermissionSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="park_name", property="parkName", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="route_ids", property="routeIds", jdbcType=JdbcType.VARCHAR)
    })
    List<ParkPermission> selectByExample(ParkPermissionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_permission
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, park_name, status, route_ids",
        "from park_permission",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="park_name", property="parkName", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="route_ids", property="routeIds", jdbcType=JdbcType.VARCHAR)
    })
    ParkPermission selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_permission
     *
     * @mbg.generated
     */
    @UpdateProvider(type=ParkPermissionSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ParkPermission record, @Param("example") ParkPermissionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_permission
     *
     * @mbg.generated
     */
    @UpdateProvider(type=ParkPermissionSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ParkPermission record, @Param("example") ParkPermissionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_permission
     *
     * @mbg.generated
     */
    @UpdateProvider(type=ParkPermissionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ParkPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_permission
     *
     * @mbg.generated
     */
    @Update({
        "update park_permission",
        "set park_name = #{parkName,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "route_ids = #{routeIds,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ParkPermission record);
}