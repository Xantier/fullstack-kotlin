allprojects {
  group = "com.packtpub"
  version = "1.0"
  repositories {
    jcenter()
  }
}

plugins {
  base 
}

dependencies {
  subprojects.forEach {
    archives(it)
  }
}
