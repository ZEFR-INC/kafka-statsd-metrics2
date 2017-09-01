build_fat_jar:
	sbt assembly

jfrog_publish:
	sbt clean publish