# ベースイメージ
FROM eclipse-temurin:21

# jarファイルコピー
RUN mkdir /app
COPY ./entorypoint.sh /app
COPY ./target/demo-*.jar /app/demo.jar

# コンテナのポートを公開
# （備忘メモ）必ずここで設定したポートが公開されるということではないので注意
EXPOSE 8080

# アプリ起動
# ENTRYPOINT ["/app/entorypoint.sh"]
CMD ["java", "-jar", "/app/demo.jar"]
