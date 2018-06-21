build-doc:
	rm -rf docs
	./gradlew javadoc --stacktrace
	.scripts/docs-jquery-cdn.sh

netlify:
	.scripts/netlify.sh