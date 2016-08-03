#1. 설치된 앱 정보

##1.1 PackageManager 생성
- Context.getPackageManager()로 생성

##1.2 List<ApplicationInfo> 받기
- getInstalledApplications(PackageManager.GET_META_DATA)

##1.3 ApplicationInfo 앱 정보 꺼내기

### ApplicationInfo 사용
- 패키지명 : ApplicationInfo.packageName

###PackageManager 사용

- 이름 : CharSequence getApplicationLabel (ApplicationInfo info)
- 이미지 : Drawable getApplicationIcon (ApplicationInfo info)