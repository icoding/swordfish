package com.baifendian.swordfish.common.job.struct.node.impexp;

import com.baifendian.swordfish.common.enums.ImpExpType;
import com.baifendian.swordfish.common.job.struct.node.impexp.reader.Reader;
import com.baifendian.swordfish.common.job.struct.node.impexp.reader.ReaderFactory;
import com.baifendian.swordfish.common.job.struct.node.impexp.setting.Setting;
import com.baifendian.swordfish.common.job.struct.node.impexp.writer.Writer;
import com.baifendian.swordfish.common.job.struct.node.impexp.writer.WriterFactory;
import com.baifendian.swordfish.dao.utils.json.JsonObjectDeserializer;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 导入导出参数构造器
 */
public class ImpExpBuilder {
  private ImpExpType type;
  @JsonRawValue
  @JsonDeserialize(using = JsonObjectDeserializer.class)
  private String reader;
  @JsonRawValue
  @JsonDeserialize(using = JsonObjectDeserializer.class)
  private String writer;
  private Setting setting;
  private Writer writerParam;
  private Reader readerParam;
  private Setting settingParam;

  public Setting getSetting() {
    return setting;
  }

  public ImpExpBuilder setSetting(Setting setting) {
    this.setting = setting;
    return this;
  }

  public ImpExpType getType() {
    return type;
  }

  public ImpExpBuilder setType(ImpExpType type) {
    this.type = type;
    return this;
  }

  public String getReader() {
    return reader;
  }

  public ImpExpBuilder setReader(String reader) {
    this.reader = reader;
    return this;
  }

  public String getWriter() {
    return writer;
  }

  public ImpExpBuilder setWriter(String writer) {
    this.writer = writer;
    return this;
  }

  public Writer getWriterParam() {
    return writerParam;
  }

  public Reader getReaderParam() {
    return readerParam;
  }

  public Setting getSettingParam() {
    return settingParam;
  }

  public ImpExpParam buildImpExp() {
    this.readerParam = ReaderFactory.getReader(this.type, this.reader);
    this.writerParam = WriterFactory.getWriter(this.type, this.writer);
    //TODO 构造setting

    return new ImpExpParam(this);
  }
}
