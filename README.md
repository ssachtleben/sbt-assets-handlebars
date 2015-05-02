# sbt-assets-handlebars

This plugin precompiles the handlebars templates to javascript code.

Add the dependency in your project's `plugins.sbt` file:

    resolvers += Resolver.url("ssachtleben sbt repository (snapshots)", url("http://ssachtleben.github.io/sbt-plugins/repository/snapshots/"))(Resolver.ivyStylePatterns)

    addSbtPlugin("com.ssachtleben.sbt" % "sbt-assets-handlebars" % "1.0.0")
    
See this plugin in action [here](https://github.com/ssachtleben/sbt-assets-example).

&copy; 2015 Sebastian Sachtleben