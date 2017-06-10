# JUnitDocMarge

このアプリケーションは、よくあるテストレポートで、なんのテストをしてるか日本語で、しかも一覧で説明を求められた時、
ユニットテストの JavaDic と、UT 実行結果の XML から自動生成できないかと考えて作ってるアプリです。

現時点では、テストがほぼ無いところ。

## 使い方:

1. create jar file.
    ```
        $ sbt assembly
        $ ls target/scala-2.11/ut_converter.jar 
        target/scala-2.11/ut_converter.jar
    ```
2. do junit test.
    ```
        $ls ut/
        TEST-net.white_azalea.UnitTest1.xml             TEST-net.white_azalea.example.UnitTest2.xml
    ```
3. create javadoc as XML.  
   using: https://github.com/MarkusBernhardt/xml-doclet
   ```$xslt
       $ ls javadoc/javadoc.xml
       javadoc/javadoc.xml
   ```
4. call this jar.
   ```$xslt
        $ java -jar ut_converter.jar javadoc/javadoc.xml ut
        SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
        SLF4J: Defaulting to no-operation (NOP) logger implementation
        SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <title>Unit Test results.</title>
            <style type="text/css">
                <!--
                body {
   ```
   後は redirection でもしてファイルにすればおｋ

## その他ヘルプ

`-h` コマンドでヘルプが見れるので、そちらを参照。  
テンプレートには [scalate](https://github.com/scalate/scalate) を採用しているので、
テンプレートを自前用意する場合はそのドキュメントを参照してください。