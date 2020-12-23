package dev.jsedano.example;

import cloud.localstack.docker.annotation.IHostNameResolver;

public class HostNameResolverImpl implements IHostNameResolver {
  @Override
  public String getHostName() {
      String dockerHost = System.getenv("DOCKER_HOST");
      return dockerHost.substring(dockerHost.lastIndexOf('/')+1, dockerHost.lastIndexOf(':'));
  }
}
