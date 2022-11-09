FROM bellsoft/liberica-openjdk-debian:17 

RUN set -x \
        && addgroup --system --gid 102 pull_point-admin \
        && adduser --system --disabled-login --ingroup pull_point-admin --no-create-home --home /nonexistent --gecos "Pull Point admin user" --shell /bin/false --uid 102 pull_point-admin \
	&& apt update \
        && apt install -y --no-install-recommends \
                gettext-base jq \
        && rm -rf /tmp/* 



RUN mkdir -p /opt/pull_point \
        && chown -R pull_point-admin:pull_point-admin /opt/pull_point \
        && mkdir -p /var/secret \
        && chown -R pull_point-admin:pull_point-admin /var/secret \
        && mkdir -p /opt/cloud \
        && chown -R pull_point-admin:pull_point-admin /opt/cloud

COPY --chown=pull_point-admin:pull_point-admin ./target/pull-point-dev.jar /opt/pull_point/
COPY --chown=pull_point-admin:pull_point-admin ./startApp.sh /opt/pull_point/

COPY --chown=pull_point-admin:pull_point-admin token.file /var/secret/hc_vault_token
COPY --chown=pull_point-admin:pull_point-admin ./Docker/configResolver.sh ./Docker/resolveConfiguration.sh ./Docker/environment.template /opt/cloud/


CMD /opt/pull_point/startApp.sh
