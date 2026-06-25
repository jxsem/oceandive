CREATE TABLE USERS(
    id UUID PRIMARY KEY DEFAULT GEN_RANDOM_UUID(),
    email VARCHAR(100) UNIQUE NOT NULL,
    display_name VARCHAR(50),
    email_verified BOOLEAN NOT NULL,
    avatar_url VARCHAR(500),
    bio VARCHAR(200),
    country VARCHAR(20),
    experience_level VARCHAR(50) DEFAULT 'NONE',
    plan_type VARCHAR(50) DEFAULT 'TRIAL',
    plan_status VARCHAR(50) DEFAULT 'ACTIVE_TRIAL',
    plan_started_at TIMESTAMP,
    plan_expires_at TIMESTAMP,
    trial_used BOOLEAN DEFAULT TRUE,
    is_banned BOOLEAN DEFAULT FALSE,
    last_login_at TIMESTAMP,
    created_at TIMESTAMP,
    update_at TIMESTAMP 
);

CREATE TABLE USER_AUTH_PROVIDERS(
    id UUID PRIMARY KEY DEFAULT GEN_RANDOM_UUID(),
    user_id UUID NOT NULL REFERENCES users(id),
    provider VARCHAR(50),
    provider_user_id VARCHAR(50),
    email VARCHAR(100),
    CONSTRAINT uq_provider_user UNIQUE (provider, provider_user_id) -- CONSTRAINT DECLARA RESTRICCION CON NOMBRE uq_provider_user, sin eso psgsql dará error críptico.
    -- GOOGLE <- QUE ES EL PROVIDER Y EL ID DE USUARIO 8495289457 <- QUE ES UNICO
);