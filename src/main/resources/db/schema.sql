/* テーブル削除：CREATE文と逆順で記載する */
DROP TABLE IF EXISTS task_t;

DROP TABLE IF EXISTS user_m;

/* ユーザーマスタ */
CREATE TABLE
  user_m (
    user_id VARCHAR(50) PRIMARY KEY,
    PASSWORD VARCHAR(100),
    user_name VARCHAR(50),
    ROLE VARCHAR(50),
    enabled BOOLEAN
  );

/* タスクテーブル */
CREATE TABLE
  task_t (
    id INT PRIMARY KEY,
    user_id VARCHAR(50),
    title VARCHAR(50),
    limitday DATE,
    complete BOOLEAN
  );
