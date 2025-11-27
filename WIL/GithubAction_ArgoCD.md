# CI + Push 방식 CD 
Github Action (Runner)가 직접 배포
Github Actions CI + Github Action CD
~~~yaml
name: CI/CD Pipeline

jobs:
  ci:
    runs-on: self-hosted
    steps:
      - run: ./gradlew build
      - run: docker build -t app .
      - run: docker push app

  cd:  # ← Push 방식 CD
    needs: ci
    runs-on: self-hosted
    steps:
      - run: docker pull app:latest
      - run: docker compose up -d  # 직접 배포
~~~

~~~yaml
jobs:
  cd:
    runs-on: self-hosted
    steps:
    - run: docker pull app:latest
    - run: docker compose up -d   # 직접 배포!
~~~

~~~yaml
### Push 방식 (GitHub Actions가 직접 배포)
```
┌──────────────┐       ┌──────────────┐       ┌──────────────┐
│   GitHub     │──────>│  EC2 Runner  │──────>│   Docker     │
│  Repository  │ push  │              │ deploy│  Container   │
└──────────────┘       └──────────────┘       └──────────────┘
                            │
                            │ docker compose up
                            ▼
                       앱이 실행됨
```
~~~
