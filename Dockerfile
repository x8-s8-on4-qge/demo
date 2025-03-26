# ベースイメージ
FROM openjdk:17

# jarファイルコピー
RUN mkdir /app
COPY ./entorypoint.sh /app
COPY ./target/demo-*.jar /app/demo.jar

# コンテナのポートを公開
# （備忘メモ）
#   ここで設定したポートが必ず公開されるという事ではなく、ドキュメント的な扱い。
#   コンテナ起動時に設定するポートフォワードでの推奨ポート。
EXPOSE 8080

# アプリ起動
# ENTRYPOINT ["/app/entorypoint.sh"]
CMD ["java", "-jar", "/app/demo.jar"]
