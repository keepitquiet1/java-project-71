.DEFAULT_GOAL := build-run

PATH1=src/test/resources/test.json
PATH2=src/test/resources/test2.json
YML1=src/test/resources/test.yml
YML2=src/test/resources/test2.yml
NEST1=src/test/resources/nested.json
NEST2=src/test/resources/nested2.json

clean:
	./gradlew clean
	@@ -20,18 +14,6 @@ build:
install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app $(PATH1) $(PATH2)

run-dist-wrong:
	./build/install/app/bin/app $(PATH1) $(PATH3)

run-dist-yml:
	./build/install/app/bin/app $(YML1) $(YML2)

run-dist-yml-wrong:
	./build/install/app/bin/app $(YML1) $(YML3)

run-dist-nest:
	./build/install/app/bin/app $(NEST1) $(NEST2)
