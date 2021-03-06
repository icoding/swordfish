/*
 * Copyright (C) 2017 Baifendian Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.baifendian.swordfish.dao.mapper;

import com.baifendian.swordfish.dao.enums.FlowStatus;
import com.baifendian.swordfish.dao.enums.NotifyType;
import com.baifendian.swordfish.dao.model.Project;
import com.baifendian.swordfish.dao.model.StreamingResult;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface StreamingResultMapper {

  /**
   * 插入记录并获取记录 id <p>
   *
   * @param result
   * @return 插入记录数
   */
  @InsertProvider(type = StreamingResultMapperProvider.class, method = "insert")
  @SelectKey(statement = "SELECT LAST_INSERT_ID() AS id", keyProperty = "result.execId", resultType = int.class, before = false)
  int insert(@Param("result") StreamingResult result);

  /**
   * 根据执行 id 查询
   *
   * @param execId
   * @return
   */
  @Results(value = {@Result(property = "execId", column = "id", id = true, javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "worker", column = "worker", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "streamingId", column = "streaming_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "name", column = "name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "desc", column = "desc", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "projectId", column = "project_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "projectName", column = "project_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "createTime", column = "create_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "modifyTime", column = "modify_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "ownerId", column = "owner_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "type", column = "type", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "parameter", column = "parameter", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "userDefinedParams", column = "user_defined_params", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "notifyType", column = "notify_type", typeHandler = EnumOrdinalTypeHandler.class, javaType = NotifyType.class, jdbcType = JdbcType.TINYINT),
      @Result(property = "notifyMails", column = "notify_mails", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "submitUserId", column = "submit_user_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "submitTime", column = "submit_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "queue", column = "queue", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "proxyUser", column = "proxy_user", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "scheduleTime", column = "schedule_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "startTime", column = "start_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "endTime", column = "end_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "status", column = "status", typeHandler = EnumOrdinalTypeHandler.class, javaType = FlowStatus.class, jdbcType = JdbcType.TINYINT),
      @Result(property = "appLinks", column = "app_links", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "jobLinks", column = "job_links", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "jobId", column = "job_id", javaType = String.class, jdbcType = JdbcType.VARCHAR)
  })
  @SelectProvider(type = StreamingResultMapperProvider.class, method = "selectById")
  StreamingResult selectById(@Param("execId") int execId);

  /**
   * 根据流 id 查询结果信息, 查询的是简单的信息
   *
   * @param streamingId
   * @return
   */
  @Results(value = {@Result(property = "execId", column = "id", id = true, javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "worker", column = "worker", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "streamingId", column = "streaming_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "parameter", column = "parameter", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "userDefinedParams", column = "user_defined_params", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "name", column = "name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "desc", column = "desc", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "projectId", column = "project_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "projectName", column = "project_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "createTime", column = "create_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "modifyTime", column = "modify_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "proxyUser", column = "proxy_user", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "queue", column = "queue", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "ownerId", column = "owner_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "owner", column = "owner_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "type", column = "type", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "notifyType", column = "notify_type", typeHandler = EnumOrdinalTypeHandler.class, javaType = NotifyType.class, jdbcType = JdbcType.TINYINT),
      @Result(property = "notifyMails", column = "notify_mails", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "submitTime", column = "submit_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "startTime", column = "start_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "endTime", column = "end_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "submitUserId", column = "submit_user_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "submitUser", column = "submit_user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "status", column = "status", typeHandler = EnumOrdinalTypeHandler.class, javaType = FlowStatus.class, jdbcType = JdbcType.TINYINT),
      @Result(property = "appLinks", column = "app_links", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "jobLinks", column = "job_links", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "jobId", column = "job_id", javaType = String.class, jdbcType = JdbcType.VARCHAR)
  })
  @SelectProvider(type = StreamingResultMapperProvider.class, method = "findLatestDetailByStreamingId")
  StreamingResult findLatestDetailByStreamingId(@Param("streamingId") int streamingId);

  /**
   * 查询没有完成的流任务
   *
   * @return
   */
  @Results(value = {@Result(property = "execId", column = "id", id = true, javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "worker", column = "worker", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "streamingId", column = "streaming_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "name", column = "name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "desc", column = "desc", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "projectId", column = "project_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "projectName", column = "project_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "createTime", column = "create_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "modifyTime", column = "modify_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "ownerId", column = "owner_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "type", column = "type", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "parameter", column = "parameter", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "userDefinedParams", column = "user_defined_params", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "notifyType", column = "notify_type", typeHandler = EnumOrdinalTypeHandler.class, javaType = NotifyType.class, jdbcType = JdbcType.TINYINT),
      @Result(property = "notifyMails", column = "notify_mails", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "submitUserId", column = "submit_user_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "submitTime", column = "submit_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "queue", column = "queue", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "proxyUser", column = "proxy_user", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "scheduleTime", column = "schedule_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "startTime", column = "start_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "endTime", column = "end_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "status", column = "status", typeHandler = EnumOrdinalTypeHandler.class, javaType = FlowStatus.class, jdbcType = JdbcType.TINYINT),
      @Result(property = "appLinks", column = "app_links", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "jobLinks", column = "job_links", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "jobId", column = "job_id", javaType = String.class, jdbcType = JdbcType.VARCHAR)
  })
  @SelectProvider(type = StreamingResultMapperProvider.class, method = "findNoFinishedJob")
  List<StreamingResult> findNoFinishedJob();

  /**
   * 更新任务信息
   *
   * @param result
   * @return
   */
  @UpdateProvider(type = StreamingResultMapperProvider.class, method = "updateResult")
  int updateResult(@Param("result") StreamingResult result);

  /**
   * 查询项目 id
   *
   * @param execId
   * @return
   */
  @Results(value = {@Result(property = "id", column = "id", id = true, javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "name", column = "name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "desc", column = "desc", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "createTime", column = "create_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
      @Result(property = "modifyTime", column = "modify_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
      @Result(property = "ownerId", column = "owner", javaType = int.class, jdbcType = JdbcType.INTEGER)
  })
  @SelectProvider(type = StreamingResultMapperProvider.class, method = "queryProject")
  Project queryProject(@Param("execId") int execId);

  /**
   * 根据执行 id 进行详细查询
   *
   * @param execId
   * @return
   */
  @Results(value = {@Result(property = "execId", column = "id", id = true, javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "worker", column = "worker", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "streamingId", column = "streaming_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "parameter", column = "parameter", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "userDefinedParams", column = "user_defined_params", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "name", column = "name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "desc", column = "desc", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "projectId", column = "project_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "projectName", column = "project_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "createTime", column = "create_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "modifyTime", column = "modify_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "proxyUser", column = "proxy_user", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "queue", column = "queue", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "ownerId", column = "owner_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "owner", column = "owner_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "type", column = "type", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "notifyType", column = "notify_type", typeHandler = EnumOrdinalTypeHandler.class, javaType = NotifyType.class, jdbcType = JdbcType.TINYINT),
      @Result(property = "notifyMails", column = "notify_mails", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "submitTime", column = "submit_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "startTime", column = "start_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "endTime", column = "end_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "submitUserId", column = "submit_user_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "submitUser", column = "submit_user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "status", column = "status", typeHandler = EnumOrdinalTypeHandler.class, javaType = FlowStatus.class, jdbcType = JdbcType.TINYINT),
      @Result(property = "appLinks", column = "app_links", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "jobLinks", column = "job_links", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "jobId", column = "job_id", javaType = String.class, jdbcType = JdbcType.VARCHAR)
  })
  @SelectProvider(type = StreamingResultMapperProvider.class, method = "findDetailByExecId")
  StreamingResult findDetailByExecId(@Param("execId") int execId);

  /**
   * 根据项目 id 和名称查询
   *
   * @param projectId
   * @param nameList
   * @return
   */
  @Results(value = {@Result(property = "execId", column = "id", id = true, javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "worker", column = "worker", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "streamingId", column = "streaming_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "parameter", column = "parameter", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "userDefinedParams", column = "user_defined_params", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "name", column = "name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "desc", column = "desc", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "projectId", column = "project_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "projectName", column = "project_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "createTime", column = "create_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "modifyTime", column = "modify_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "proxyUser", column = "proxy_user", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "queue", column = "queue", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "ownerId", column = "owner_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "owner", column = "owner_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "type", column = "type", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "notifyType", column = "notify_type", typeHandler = EnumOrdinalTypeHandler.class, javaType = NotifyType.class, jdbcType = JdbcType.TINYINT),
      @Result(property = "notifyMails", column = "notify_mails", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "submitTime", column = "submit_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "startTime", column = "start_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "endTime", column = "end_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "submitUserId", column = "submit_user_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "submitUser", column = "submit_user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "status", column = "status", typeHandler = EnumOrdinalTypeHandler.class, javaType = FlowStatus.class, jdbcType = JdbcType.TINYINT),
      @Result(property = "appLinks", column = "app_links", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "jobLinks", column = "job_links", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "jobId", column = "job_id", javaType = String.class, jdbcType = JdbcType.VARCHAR)
  })
  @SelectProvider(type = StreamingResultMapperProvider.class, method = "findLatestDetailByProjectAndNames")
  List<StreamingResult> findLatestDetailByProjectAndNames(@Param("projectId") int projectId, @Param("nameList") List<String> nameList);

  /**
   * 根据条件查询数目
   *
   * @param projectId
   * @param name
   * @param startDate
   * @param endDate
   * @param status
   * @return
   */
  @SelectProvider(type = StreamingResultMapperProvider.class, method = "findCountByMultiCondition")
  int findCountByMultiCondition(@Param("projectId") int projectId, @Param("name") String name, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("status") List<Integer> status);

  /**
   * 根据条件查询结果
   *
   * @param projectId
   * @param name
   * @param startDate
   * @param endDate
   * @param status
   * @param start
   * @param limit
   * @return
   */
  @Results(value = {@Result(property = "execId", column = "id", id = true, javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "worker", column = "worker", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "streamingId", column = "streaming_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "parameter", column = "parameter", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "userDefinedParams", column = "user_defined_params", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "name", column = "name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "desc", column = "desc", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "projectId", column = "project_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "projectName", column = "project_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "createTime", column = "create_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "modifyTime", column = "modify_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "proxyUser", column = "proxy_user", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "queue", column = "queue", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "ownerId", column = "owner_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "owner", column = "owner_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "type", column = "type", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "notifyType", column = "notify_type", typeHandler = EnumOrdinalTypeHandler.class, javaType = NotifyType.class, jdbcType = JdbcType.TINYINT),
      @Result(property = "notifyMails", column = "notify_mails", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "submitTime", column = "submit_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "startTime", column = "start_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "endTime", column = "end_time", javaType = Timestamp.class, jdbcType = JdbcType.DATE),
      @Result(property = "submitUserId", column = "submit_user_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
      @Result(property = "submitUser", column = "submit_user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "status", column = "status", typeHandler = EnumOrdinalTypeHandler.class, javaType = FlowStatus.class, jdbcType = JdbcType.TINYINT),
      @Result(property = "appLinks", column = "app_links", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "jobLinks", column = "job_links", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Result(property = "jobId", column = "job_id", javaType = String.class, jdbcType = JdbcType.VARCHAR)
  })
  @SelectProvider(type = StreamingResultMapperProvider.class, method = "findByMultiCondition")
  List<StreamingResult> findByMultiCondition(@Param("projectId") int projectId, @Param("name") String name, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("status") List<Integer> status, @Param("start") int start, @Param("limit") int limit);
}
