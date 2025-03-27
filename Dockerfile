# ベースイメージ
FROM eclipse-temurin:21

# 作業ディレクトリ設定
WORKDIR /app
# モジュールをコンテナにコピー
COPY ./target/demo-*.jar /app/demo.jar

# コンテナの公開ポート設定
EXPOSE 8080

# アプリ起動
# CMD ["java", "-jar", "/app/demo.jar"]
ENTRYPOINT ["java", "-jar", "/app/demo.jar"]
