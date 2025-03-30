# 2024-ITDS361-Gemini4
### Team members
- นายธนวิชญ์ จึงกิจธนวัฒน์ 6587017
- นายจิตรกัณฐ์ ดำรงตระกูลวัฒน์ 6587051
- นายณปภัช นภัสนันท์ 6587053
- นายธัชกร โกมลคงอยู่ 6587055
- นายสันติภาพ โชติมานนท์ 6587105

## Gemini Deliverable 4
### Front-end
- เข้าผ่าน terminal แล้ว cd ไปยัง folder gemini4_frontend
- จากนั้น npm i
- แล้วจึง npm run dev
- จะทำการเปิด localhost ของ front-end แล้วเข้าไปยังหน้าเว็บผ่าน http://localhost:5173/
- ทำการสร้าง user ด้วย register
- จากนั้นทดสอบการเข้าใช้ user ผ่านการ login

### Back-end
- เข้าไปยัง folder gemini4-backend
- ทำการ set project structure เป็น sdk corretto-17
- ทำการ set application.properties ในส่วนของ spring.datasource.url=jdbc:h2:file:<"ตามด้วย full path ของ folder gemini4-backend">users.db
- จากนั้น run file Gemini4BackendApplication
- เข้าเช็ค back-end ผ่าน http://localhost:8080/
