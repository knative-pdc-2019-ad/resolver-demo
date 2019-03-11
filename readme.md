## Knative eventing demo - WIP

### Keywords
- knative
  - build
  - serving
  - eventing
- serverless
- riff
- spring cloud function

### Setup

#### Create Channel
```sh
kubectl apply -f https://github.com/knative-pdc-2019-ad/parser-resolver-channels/blob/master/usage_events.yaml
```

#### Create Function
```sh
VER=m1

riff function create kn-resolver \
  --local-path /Users/priyank.srivastava/Documents/workspace/personal/pdc-2019/resolver-demo \
  --image $DOCKER_HUB_ID/kn-resolver:${VER} \
  --verbose

riff function create kn-resolver \
  --git-repo https://github.com/knative-pdc-2019-ad/resolver-demo.git  \
  --image $DOCKER_HUB_ID/kn-resolver:v2 \
  --verbose

```

#### Subscriber - Log
```sh
kubectl apply -f cfg/subscriber-log.yaml
```