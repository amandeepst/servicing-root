package com.worldpay.servicing.model.util;

import org.hibernate.Session;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

public class StringSequenceIdentifier implements IdentifierGenerator, Configurable {

  public static final String SEQUENCE_PREFIX = "sequence_prefix";

  private String sequenceCallSyntax;

  @Override
  public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) {
    final JdbcEnvironment jdbcEnvironment = serviceRegistry.getService(JdbcEnvironment.class);
    final Dialect dialect = jdbcEnvironment.getDialect();

    final String sequencePerEntitySuffix = ConfigurationHelper.getString(
        SequenceStyleGenerator.CONFIG_SEQUENCE_PER_ENTITY_SUFFIX, params,
        SequenceStyleGenerator.DEF_SEQUENCE_SUFFIX);

    final String defaultSequenceName = ConfigurationHelper
        .getBoolean(SequenceStyleGenerator.CONFIG_PREFER_SEQUENCE_PER_ENTITY, params, false)
        ? (params.getProperty(JPA_ENTITY_NAME) + sequencePerEntitySuffix)
        : SequenceStyleGenerator.DEF_SEQUENCE_NAME;

    sequenceCallSyntax = dialect.getSequenceNextValString(
        ConfigurationHelper
            .getString(SequenceStyleGenerator.SEQUENCE_PARAM, params, defaultSequenceName));
  }

  @SuppressWarnings("unchecked")
  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object) {
    if (object instanceof Identifiable) {
      Identifiable<String> identifiable = (Identifiable<String>) object;
      Serializable id = identifiable.getId();
      if (id != null && id != "") {
        return id;
      }
    }
    long seqValue = ((Number) Session.class.cast(session).createSQLQuery(sequenceCallSyntax)
        .uniqueResult())
        .longValue();

    return String.valueOf(seqValue);
  }
}
