# 2024-ITDS361-Gemini4
### Team members
- นายธนวิชญ์ จึงกิจธนวัฒน์ 6587017
- นายจิตรกัณฐ์ ดำรงตระกูลวัฒน์ 6587051
- นายณปภัช นภัสนันท์ 6587053
- นายธัชกร โกมลคงอยู่ 6587055
- นายสันติภาพ โชติมานนท์ 6587105

## Gemini Deliverable 4
## Front-end
- เข้าผ่าน terminal แล้ว cd ไปยัง folder gemini4_frontend ด้วยคำสั่ง cd .\implementations\gemini4_frontend\
- จากนั้น npm i เพื่อเรียก library ที่จำเป็น
- แล้วจึง npm run dev เพื่อเริ่มต้นการทำงาน
- จะทำการเปิด localhost ของ front-end แล้วเข้าไปยังหน้าเว็บผ่าน http://localhost:5173/
- ทำการสร้าง user ด้วย register
- จากนั้นทดสอบการเข้าใช้ user ผ่านการ login

## Back-end
- เข้าไปยัง folder gemini4-backend ด้วยคำสั่ง cd .\implementations\gemini4-backend\
- ทำการ set project structure เป็น sdk corretto-17
- ทำการ set application.properties ในส่วนของ spring.datasource.url=jdbc:h2:file:<"ตามด้วย full path ของ folder gemini4-backend">users.db
- จากนั้น run file Gemini4BackendApplication
- เข้าเช็ค back-end ผ่าน http://localhost:8080/
## Design Pattern
Factory Design Pattern ใน class UserFactory ที่มีการสร้าง object จาก role ที่รับมาจากการ Register ของผู้ใช้
- เหตุผลที่เลือกใช้ เพราะ ในระบบ Gemini จำเป็นต้องรองรับผู้ใช้ในหลาย role และมีความจำเป็นต้องสร้าง instance ของ User ในแต่ละ role ตอนทำการ Register 
- Factory method จึงช่วยลดความซ้ำซ้อนของโค้ดในส่วน UserController นอกจากนี้ยังช่วยรองรับหากมีความจำเป็นต้องเพิ่ม role อื่น ๆ เพิ่มเติมในอนาคตยังสามารถเข้ามาเพิ่ม role ใน method นี้ได้ทันที
