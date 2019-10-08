팀 Anty-Beety의 안전 길찾기 서비스 ThinkBeeWay의 Spring Project
1.ThinkBeeWay는?

범죄 예방의 방법 중에 하나로 환경 설계를 통한 범죄 예방이 있습니다. 지역의 환경이 범죄율에
직/간접적 영향을 끼친다는 이론에 따라 인위적으로 환경을 바꾸는 방법입니다. 이 방법은 범죄
시도를 좌절 시키는 것 이외에도 범죄 공포를 낮추는 효과가 있습니다. 저희 프로젝트는 도로의 
환경적 요소를 분석하여 범죄 공포를 추측하여 안전한 길을 찾는 것을 목표로 합니다.

2. 개발환경 및 개발언어
개발 환경은 DB로 mysql을, IDE로는 IntelliJ를 사용하였고, 안드로이드를 사용하여 앱을 개발하였습니다. 사용 언어로는 앱 개발을 위해 JAVA를, 안전한 길 찾기 모델을 위해 Python을 사용하였습니다.

3. 프로젝트 주요기능 
ThinkBeeWay는 안전한 귀갓길을 찾는 것을 도와줍니다. 기존의 도로 검색 시스템은 출발 장소와 도착 장소까지 가장 가까운 길만 탐색합니다. ThinkBeeWay는 출발 장소와 도착 장소 사이의 도로를  탐색하고 도로의 안전 점수에 따라 길을 안내합니다. 안전 점수는 도로의 환경적 요소에 따른
안전 점수 채점 모델에 따라 채점됩니다. 모델은 서포트 벡터 머신(SVM)으로, 이를 사용하여 해당 도로가 가진 환경적 요소에 따른 안전 점수를 판별합니다.

또한 ThinkBeeWay는 사용자로 하여금 위험 요소를 신고하는 기능을 제공합니다. 해당 장소에 범죄 공포 또는 위협을 느꼈을 때, 카테고리에 맞게 신고를 하는 기능입니다. 이런 기능을 통해, ThinkBeeWay는 안전 점수 채점 모델을 강화하여 더욱 설득력 있는 길을 제시할 수 있고, 
사용자는 신고 내역을 확인함으로써 현재 지역에 어떤 범죄 위협이 발생했는지 확인할 수 있습니다.
현재 ThinkBeeWay는 봉천동 내의 길 찾기를 제공하고 있으며, 추후 업데이트로 서울시 지원을 목표로 하고 있습니다.

4. 기대효과 및 활용분야
ThinkBeeWay의 안전 길 찾기는 귀갓길 뿐만 아니라 모든 길에도 적용됩니다. ThinkBeeWay를 통해
범죄 불안감이 줄어든 길을 찾을 수 있습니다. 사용자가 범죄 위협에 관한 신고를 하면서 지역의 범죄 공포 요소를 빨리 알 수 있습니다. 길에 부여되는 가중치의 강도를 조절할 수 있기 때문에
사용자는 기존의 길찾기에 안전 요소가 들어가는 비중을 조절할 수 있습니다. 
ThinkBeeWay는 안전 시설물을 사용하여 길 찾기를 하기 때문에 응용력이 높습니다. 환경적 요소를 추가하거나 모델을 업데이트하여 더 나은 길을 찾을 수 있습니다. 안전 시설물은 도로에만 해당하는 것이 아니기 때문에 지역적 요소도 될 수 있습니다. 이를 사용하여 순찰 지역을 수정하거나 불안 지역의 순찰을 강화할 수도 있습니다. 

