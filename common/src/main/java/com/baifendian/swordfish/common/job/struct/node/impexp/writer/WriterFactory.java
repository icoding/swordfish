package com.baifendian.swordfish.common.job.struct.node.impexp.writer;

import com.baifendian.swordfish.common.enums.ImpExpType;
import com.baifendian.swordfish.dao.utils.json.JsonUtil;
import org.apache.avro.data.Json;

/**
 * 写配置工厂
 */
public class WriterFactory {

  public static Writer getWriter(ImpExpType type, String writer) {
    switch (type) {
      case MYSQL_TO_HDFS:
        return JsonUtil.parseObject(writer, HdfsWriter.class);
      case MYSQL_TO_HIVE:
        return JsonUtil.parseObject(writer, HiveWriter.class);
      default:
        return null;
    }
  }
}
