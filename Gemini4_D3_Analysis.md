# Use case description

## Install new configurations to the system
---
| **Element**              | **Description** |
|--------------------------|-----------------|
| **Use Case Name**        | Install new configurations to the system |
| **ID**                   | 01 |
| **Importance Level**     | High |
| **Primary Actor**        | Support |
| **Use Case Type**        | Detail |
| **Stakeholders and Interests** | - **Support**: ต้องการให้ระบบทำงานได้อย่างมีประสิทธิภาพหลังจากติดตั้งค่ากำหนดใหม่ <br> - **System Administrators**: ต้องตรวจสอบและจัดการการกำหนดค่าของระบบ <br> - **Telescope Operators**: ต้องใช้งานระบบที่อัปเดตแล้วโดยไม่มีปัญหา <br> - **Astronomers and Science Observers**: ต้องการให้ระบบทำงานได้ตามปกติหลังจากการอัปเดต |
| **Brief Description**    | เป็น Use Case ที่อธิบายกระบวนการติดตั้งและใช้งานการกำหนดค่าระบบใหม่ใน Gemini Telescope Control System (GTCS) โดย Support จะทำการอัปเดตการตั้งค่าต่าง ๆ เพื่อปรับปรุงประสิทธิภาพ, ความปลอดภัย, และความเข้ากันได้กับอุปกรณ์หรือส่วนประกอบใหม่ ๆ ของระบบ |
| **Trigger**              | - มีการอัปเดตค่ากำหนดใหม่ที่ต้องติดตั้ง <br> - มีคำขอจาก Administrator หรือ Telescope Operator ให้เปลี่ยนแปลงค่ากำหนดของระบบ |
| **Type**                 | External |
| **Relationships**        | - **Association**: Support |
| **Normal Flow of Events**|  1. Support เข้าสู่ระบบ <br> 2. Support เข้าถึง Configuration Manager <br> 3. Support อัปโหลดหรือเลือกไฟล์ค่ากำหนดใหม่ <br> 4. ระบบตรวจสอบความถูกต้องของไฟล์ค่ากำหนด (S-1, S-2) <br> 5. ระบบรีสตาร์ท <br> 6. ระบบบันทึกการเปลี่ยนแปลงเพื่อใช้ตรวจสอบย้อนหลัง <br> 7. ระบบแจ้งให้ฝ่ายสนับสนุนทราบว่าการติดตั้งสำเร็จ |
| **Subflows**             | **S-1:** ระบบตรวจสอบ Syntax, ความเข้ากันได้, และเงื่อนไขที่เกี่ยวข้อง; หากพบข้อผิดพลาด ระบบจะปฏิเสธไฟล์ค่ากำหนดนั้น <br> **S-2:** ระบบตรวจสอบ Syntax, ความเข้ากันได้, และเงื่อนไขที่เกี่ยวข้อง; หากการตรวจสอบผ่าน ระบบจะติดตั้งค่ากำหนดใหม่ และโหลดค่ากำหนดใหม่ |
| **Alternate/Exceptional Flow** | **การตรวจสอบไฟล์ค่ากำหนดล้มเหลว:** <br> - ระบบปฏิเสธไฟล์ค่ากำหนด <br> - ฝ่ายสนับสนุนตรวจสอบบันทึกข้อผิดพลาดและแก้ไขไฟล์ <br> **ระบบติดตั้งค่ากำหนดใหม่ไม่สำเร็จ:** <br> - ระบบเริ่มต้นกระบวนการย้อนกลับ <br>
- ระบบคืนค่ากำหนดก่อนหน้า <br> - ระบบแสดงข้อความข้อผิดพลาดให้ฝ่ายสนับสนุนทราบ |

![ITDS361_All Diagram - 1  Install new configuration Support](https://github.com/user-attachments/assets/55e96c41-2017-4399-8d4e-f261833dc68d)
