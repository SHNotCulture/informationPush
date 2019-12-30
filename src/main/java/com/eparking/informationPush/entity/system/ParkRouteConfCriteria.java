package com.eparking.informationPush.entity.system;

import java.util.ArrayList;
import java.util.List;

public class ParkRouteConfCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    public ParkRouteConfCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andParkIdIsNull() {
            addCriterion("park_id is null");
            return (Criteria) this;
        }

        public Criteria andParkIdIsNotNull() {
            addCriterion("park_id is not null");
            return (Criteria) this;
        }

        public Criteria andParkIdEqualTo(Integer value) {
            addCriterion("park_id =", value, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdNotEqualTo(Integer value) {
            addCriterion("park_id <>", value, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdGreaterThan(Integer value) {
            addCriterion("park_id >", value, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("park_id >=", value, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdLessThan(Integer value) {
            addCriterion("park_id <", value, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdLessThanOrEqualTo(Integer value) {
            addCriterion("park_id <=", value, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdIn(List<Integer> values) {
            addCriterion("park_id in", values, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdNotIn(List<Integer> values) {
            addCriterion("park_id not in", values, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdBetween(Integer value1, Integer value2) {
            addCriterion("park_id between", value1, value2, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdNotBetween(Integer value1, Integer value2) {
            addCriterion("park_id not between", value1, value2, "parkId");
            return (Criteria) this;
        }

        public Criteria andRouteIdIsNull() {
            addCriterion("route_id is null");
            return (Criteria) this;
        }

        public Criteria andRouteIdIsNotNull() {
            addCriterion("route_id is not null");
            return (Criteria) this;
        }

        public Criteria andRouteIdEqualTo(Integer value) {
            addCriterion("route_id =", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdNotEqualTo(Integer value) {
            addCriterion("route_id <>", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdGreaterThan(Integer value) {
            addCriterion("route_id >", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("route_id >=", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdLessThan(Integer value) {
            addCriterion("route_id <", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdLessThanOrEqualTo(Integer value) {
            addCriterion("route_id <=", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdIn(List<Integer> values) {
            addCriterion("route_id in", values, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdNotIn(List<Integer> values) {
            addCriterion("route_id not in", values, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdBetween(Integer value1, Integer value2) {
            addCriterion("route_id between", value1, value2, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("route_id not between", value1, value2, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteParkIdIsNull() {
            addCriterion("route_park_id is null");
            return (Criteria) this;
        }

        public Criteria andRouteParkIdIsNotNull() {
            addCriterion("route_park_id is not null");
            return (Criteria) this;
        }

        public Criteria andRouteParkIdEqualTo(String value) {
            addCriterion("route_park_id =", value, "routeParkId");
            return (Criteria) this;
        }

        public Criteria andRouteParkIdNotEqualTo(String value) {
            addCriterion("route_park_id <>", value, "routeParkId");
            return (Criteria) this;
        }

        public Criteria andRouteParkIdGreaterThan(String value) {
            addCriterion("route_park_id >", value, "routeParkId");
            return (Criteria) this;
        }

        public Criteria andRouteParkIdGreaterThanOrEqualTo(String value) {
            addCriterion("route_park_id >=", value, "routeParkId");
            return (Criteria) this;
        }

        public Criteria andRouteParkIdLessThan(String value) {
            addCriterion("route_park_id <", value, "routeParkId");
            return (Criteria) this;
        }

        public Criteria andRouteParkIdLessThanOrEqualTo(String value) {
            addCriterion("route_park_id <=", value, "routeParkId");
            return (Criteria) this;
        }

        public Criteria andRouteParkIdLike(String value) {
            addCriterion("route_park_id like", value, "routeParkId");
            return (Criteria) this;
        }

        public Criteria andRouteParkIdNotLike(String value) {
            addCriterion("route_park_id not like", value, "routeParkId");
            return (Criteria) this;
        }

        public Criteria andRouteParkIdIn(List<String> values) {
            addCriterion("route_park_id in", values, "routeParkId");
            return (Criteria) this;
        }

        public Criteria andRouteParkIdNotIn(List<String> values) {
            addCriterion("route_park_id not in", values, "routeParkId");
            return (Criteria) this;
        }

        public Criteria andRouteParkIdBetween(String value1, String value2) {
            addCriterion("route_park_id between", value1, value2, "routeParkId");
            return (Criteria) this;
        }

        public Criteria andRouteParkIdNotBetween(String value1, String value2) {
            addCriterion("route_park_id not between", value1, value2, "routeParkId");
            return (Criteria) this;
        }

        public Criteria andRouteParkNameIsNull() {
            addCriterion("route_park_name is null");
            return (Criteria) this;
        }

        public Criteria andRouteParkNameIsNotNull() {
            addCriterion("route_park_name is not null");
            return (Criteria) this;
        }

        public Criteria andRouteParkNameEqualTo(String value) {
            addCriterion("route_park_name =", value, "routeParkName");
            return (Criteria) this;
        }

        public Criteria andRouteParkNameNotEqualTo(String value) {
            addCriterion("route_park_name <>", value, "routeParkName");
            return (Criteria) this;
        }

        public Criteria andRouteParkNameGreaterThan(String value) {
            addCriterion("route_park_name >", value, "routeParkName");
            return (Criteria) this;
        }

        public Criteria andRouteParkNameGreaterThanOrEqualTo(String value) {
            addCriterion("route_park_name >=", value, "routeParkName");
            return (Criteria) this;
        }

        public Criteria andRouteParkNameLessThan(String value) {
            addCriterion("route_park_name <", value, "routeParkName");
            return (Criteria) this;
        }

        public Criteria andRouteParkNameLessThanOrEqualTo(String value) {
            addCriterion("route_park_name <=", value, "routeParkName");
            return (Criteria) this;
        }

        public Criteria andRouteParkNameLike(String value) {
            addCriterion("route_park_name like", value, "routeParkName");
            return (Criteria) this;
        }

        public Criteria andRouteParkNameNotLike(String value) {
            addCriterion("route_park_name not like", value, "routeParkName");
            return (Criteria) this;
        }

        public Criteria andRouteParkNameIn(List<String> values) {
            addCriterion("route_park_name in", values, "routeParkName");
            return (Criteria) this;
        }

        public Criteria andRouteParkNameNotIn(List<String> values) {
            addCriterion("route_park_name not in", values, "routeParkName");
            return (Criteria) this;
        }

        public Criteria andRouteParkNameBetween(String value1, String value2) {
            addCriterion("route_park_name between", value1, value2, "routeParkName");
            return (Criteria) this;
        }

        public Criteria andRouteParkNameNotBetween(String value1, String value2) {
            addCriterion("route_park_name not between", value1, value2, "routeParkName");
            return (Criteria) this;
        }

        public Criteria andInParkPortIsNull() {
            addCriterion("in_park_port is null");
            return (Criteria) this;
        }

        public Criteria andInParkPortIsNotNull() {
            addCriterion("in_park_port is not null");
            return (Criteria) this;
        }

        public Criteria andInParkPortEqualTo(String value) {
            addCriterion("in_park_port =", value, "inParkPort");
            return (Criteria) this;
        }

        public Criteria andInParkPortNotEqualTo(String value) {
            addCriterion("in_park_port <>", value, "inParkPort");
            return (Criteria) this;
        }

        public Criteria andInParkPortGreaterThan(String value) {
            addCriterion("in_park_port >", value, "inParkPort");
            return (Criteria) this;
        }

        public Criteria andInParkPortGreaterThanOrEqualTo(String value) {
            addCriterion("in_park_port >=", value, "inParkPort");
            return (Criteria) this;
        }

        public Criteria andInParkPortLessThan(String value) {
            addCriterion("in_park_port <", value, "inParkPort");
            return (Criteria) this;
        }

        public Criteria andInParkPortLessThanOrEqualTo(String value) {
            addCriterion("in_park_port <=", value, "inParkPort");
            return (Criteria) this;
        }

        public Criteria andInParkPortLike(String value) {
            addCriterion("in_park_port like", value, "inParkPort");
            return (Criteria) this;
        }

        public Criteria andInParkPortNotLike(String value) {
            addCriterion("in_park_port not like", value, "inParkPort");
            return (Criteria) this;
        }

        public Criteria andInParkPortIn(List<String> values) {
            addCriterion("in_park_port in", values, "inParkPort");
            return (Criteria) this;
        }

        public Criteria andInParkPortNotIn(List<String> values) {
            addCriterion("in_park_port not in", values, "inParkPort");
            return (Criteria) this;
        }

        public Criteria andInParkPortBetween(String value1, String value2) {
            addCriterion("in_park_port between", value1, value2, "inParkPort");
            return (Criteria) this;
        }

        public Criteria andInParkPortNotBetween(String value1, String value2) {
            addCriterion("in_park_port not between", value1, value2, "inParkPort");
            return (Criteria) this;
        }

        public Criteria andOutParkPortIsNull() {
            addCriterion("out_park_port is null");
            return (Criteria) this;
        }

        public Criteria andOutParkPortIsNotNull() {
            addCriterion("out_park_port is not null");
            return (Criteria) this;
        }

        public Criteria andOutParkPortEqualTo(String value) {
            addCriterion("out_park_port =", value, "outParkPort");
            return (Criteria) this;
        }

        public Criteria andOutParkPortNotEqualTo(String value) {
            addCriterion("out_park_port <>", value, "outParkPort");
            return (Criteria) this;
        }

        public Criteria andOutParkPortGreaterThan(String value) {
            addCriterion("out_park_port >", value, "outParkPort");
            return (Criteria) this;
        }

        public Criteria andOutParkPortGreaterThanOrEqualTo(String value) {
            addCriterion("out_park_port >=", value, "outParkPort");
            return (Criteria) this;
        }

        public Criteria andOutParkPortLessThan(String value) {
            addCriterion("out_park_port <", value, "outParkPort");
            return (Criteria) this;
        }

        public Criteria andOutParkPortLessThanOrEqualTo(String value) {
            addCriterion("out_park_port <=", value, "outParkPort");
            return (Criteria) this;
        }

        public Criteria andOutParkPortLike(String value) {
            addCriterion("out_park_port like", value, "outParkPort");
            return (Criteria) this;
        }

        public Criteria andOutParkPortNotLike(String value) {
            addCriterion("out_park_port not like", value, "outParkPort");
            return (Criteria) this;
        }

        public Criteria andOutParkPortIn(List<String> values) {
            addCriterion("out_park_port in", values, "outParkPort");
            return (Criteria) this;
        }

        public Criteria andOutParkPortNotIn(List<String> values) {
            addCriterion("out_park_port not in", values, "outParkPort");
            return (Criteria) this;
        }

        public Criteria andOutParkPortBetween(String value1, String value2) {
            addCriterion("out_park_port between", value1, value2, "outParkPort");
            return (Criteria) this;
        }

        public Criteria andOutParkPortNotBetween(String value1, String value2) {
            addCriterion("out_park_port not between", value1, value2, "outParkPort");
            return (Criteria) this;
        }

        public Criteria andXkzhIsNull() {
            addCriterion("xkzh is null");
            return (Criteria) this;
        }

        public Criteria andXkzhIsNotNull() {
            addCriterion("xkzh is not null");
            return (Criteria) this;
        }

        public Criteria andXkzhEqualTo(String value) {
            addCriterion("xkzh =", value, "xkzh");
            return (Criteria) this;
        }

        public Criteria andXkzhNotEqualTo(String value) {
            addCriterion("xkzh <>", value, "xkzh");
            return (Criteria) this;
        }

        public Criteria andXkzhGreaterThan(String value) {
            addCriterion("xkzh >", value, "xkzh");
            return (Criteria) this;
        }

        public Criteria andXkzhGreaterThanOrEqualTo(String value) {
            addCriterion("xkzh >=", value, "xkzh");
            return (Criteria) this;
        }

        public Criteria andXkzhLessThan(String value) {
            addCriterion("xkzh <", value, "xkzh");
            return (Criteria) this;
        }

        public Criteria andXkzhLessThanOrEqualTo(String value) {
            addCriterion("xkzh <=", value, "xkzh");
            return (Criteria) this;
        }

        public Criteria andXkzhLike(String value) {
            addCriterion("xkzh like", value, "xkzh");
            return (Criteria) this;
        }

        public Criteria andXkzhNotLike(String value) {
            addCriterion("xkzh not like", value, "xkzh");
            return (Criteria) this;
        }

        public Criteria andXkzhIn(List<String> values) {
            addCriterion("xkzh in", values, "xkzh");
            return (Criteria) this;
        }

        public Criteria andXkzhNotIn(List<String> values) {
            addCriterion("xkzh not in", values, "xkzh");
            return (Criteria) this;
        }

        public Criteria andXkzhBetween(String value1, String value2) {
            addCriterion("xkzh between", value1, value2, "xkzh");
            return (Criteria) this;
        }

        public Criteria andXkzhNotBetween(String value1, String value2) {
            addCriterion("xkzh not between", value1, value2, "xkzh");
            return (Criteria) this;
        }

        public Criteria andPublicKeyIsNull() {
            addCriterion("public_key is null");
            return (Criteria) this;
        }

        public Criteria andPublicKeyIsNotNull() {
            addCriterion("public_key is not null");
            return (Criteria) this;
        }

        public Criteria andPublicKeyEqualTo(String value) {
            addCriterion("public_key =", value, "publicKey");
            return (Criteria) this;
        }

        public Criteria andPublicKeyNotEqualTo(String value) {
            addCriterion("public_key <>", value, "publicKey");
            return (Criteria) this;
        }

        public Criteria andPublicKeyGreaterThan(String value) {
            addCriterion("public_key >", value, "publicKey");
            return (Criteria) this;
        }

        public Criteria andPublicKeyGreaterThanOrEqualTo(String value) {
            addCriterion("public_key >=", value, "publicKey");
            return (Criteria) this;
        }

        public Criteria andPublicKeyLessThan(String value) {
            addCriterion("public_key <", value, "publicKey");
            return (Criteria) this;
        }

        public Criteria andPublicKeyLessThanOrEqualTo(String value) {
            addCriterion("public_key <=", value, "publicKey");
            return (Criteria) this;
        }

        public Criteria andPublicKeyLike(String value) {
            addCriterion("public_key like", value, "publicKey");
            return (Criteria) this;
        }

        public Criteria andPublicKeyNotLike(String value) {
            addCriterion("public_key not like", value, "publicKey");
            return (Criteria) this;
        }

        public Criteria andPublicKeyIn(List<String> values) {
            addCriterion("public_key in", values, "publicKey");
            return (Criteria) this;
        }

        public Criteria andPublicKeyNotIn(List<String> values) {
            addCriterion("public_key not in", values, "publicKey");
            return (Criteria) this;
        }

        public Criteria andPublicKeyBetween(String value1, String value2) {
            addCriterion("public_key between", value1, value2, "publicKey");
            return (Criteria) this;
        }

        public Criteria andPublicKeyNotBetween(String value1, String value2) {
            addCriterion("public_key not between", value1, value2, "publicKey");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table park_route_conf
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table park_route_conf
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}