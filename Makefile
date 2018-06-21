build-doc:
	./gradlew javadoc --stacktrace

netlify:
	.scripts/netlify.sh