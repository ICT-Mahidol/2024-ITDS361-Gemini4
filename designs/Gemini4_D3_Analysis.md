# Software Analysis Artifacts
## Use Case
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
| **Subflows**             | **S-1:** <br>1. ระบบตรวจสอบ Syntax ความเข้ากันได้, และเงื่อนไขที่เกี่ยวข้อง<br> 2. หากพบข้อผิดพลาด ระบบจะปฏิเสธไฟล์ค่ากำหนดนั้น <br> **S-2:** <br>1. ระบบตรวจสอบ Syntax ความเข้ากันได้ และเงื่อนไขที่เกี่ยวข้อง<br> 2. หากการตรวจสอบผ่าน ระบบจะติดตั้งค่ากำหนดใหม่ และโหลดค่ากำหนดใหม่ |
| **Alternate/Exceptional Flow** | **การตรวจสอบไฟล์ค่ากำหนดล้มเหลว:** <br> - ระบบปฏิเสธไฟล์ค่ากำหนด <br> - ฝ่ายสนับสนุนตรวจสอบบันทึกข้อผิดพลาดและแก้ไขไฟล์ <br> **ระบบติดตั้งค่ากำหนดใหม่ไม่สำเร็จ:** <br> - ระบบเริ่มต้นกระบวนการย้อนกลับ <br> - ระบบคืนค่ากำหนดก่อนหน้า <br> - ระบบแสดงข้อความข้อผิดพลาดให้ฝ่ายสนับสนุนทราบ |

![ITDS361_All Diagram - 1  Install new configuration Support-1](https://github.com/user-attachments/assets/42ef336c-5d99-480f-9794-d5a6ed937dbe)
![ITDS361_All Diagram - 1  Install new configuration Support-2](https://github.com/user-attachments/assets/98b36c2a-f740-4c75-bf78-fcf45eef1144)
-----
| **Element**              | **Description** |
|--------------------------|-----------------|
| **Use Case Name**        | Operate the interactive observing (virtual telescope) |
| **ID**                   | 02 |
| **Importance Level**     | High |
| **Primary Actor**        | Astronomer |
| **Use Case Type**        | Detail |
| **Stakeholders and Interests** | - **Astronomer**: ต้องการใช้งานกล้องโทรทรรศน์เสมือนเพื่อทดสอบการสังเกตการณ์ก่อนการใช้เวลากล้องโทรทรรศน์จริง <br> - **Science Observer**: สนใจข้อมูลที่เก็บรวบรวมระหว่างการสังเกตการณ์เสมือน <br> - **Administrator**: กังวลเกี่ยวกับทรัพยากรระบบที่ใช้ระหว่างการสังเกตการณ์เสมือน <br> - **Support**: ตรวจสอบว่ากล้องโทรทรรศน์เสมือนทำงานอย่างถูกต้อง |
| **Brief Description**    | Use case นี้อนุญาตให้นักดาราศาสตร์โต้ตอบกับการจำลองกล้องโทรทรรศน์เสมือนเพื่อเตรียมและทดสอบเทคนิคการสังเกตการณ์, ทำความคุ้นเคยกับการควบคุมกล้องโทรทรรศน์, และตรวจสอบแผนวิทยาศาสตร์ของพวกเขาในสภาพแวดล้อมจำลองก่อนที่จะส่งไปใช้เวลากล้องโทรทรรศน์จริง |
| **Trigger**              | นักดาราศาสตร์เลือกที่จะดำเนินการกล้องโทรทรรศน์เสมือนหลังจากทดสอบแผนวิทยาศาสตร์ |
| **Type**                 | External, User-initiated |
| **Relationships**        | **Association**: Astronomer |
| **Normal Flow of Events**| 1. ระบบแสดงอินเทอร์เฟซกล้องโทรทรรศน์เสมือน <br> 2. Astronomer เลือกวัตถุท้องฟ้าเป้าหมายจากแคตตาล็อกดาว <br> 3. Astronomer กำหนดค่าพารามิเตอร์การสังเกตการณ์ (เวลาการรับแสง, ฟิลเตอร์, ฯลฯ) <br> 4. Astronomer เริ่มต้นการสังเกตการณ์เสมือน <br> 5. ระบบจำลองการทำงานของกล้องโทรทรรศน์และกระบวนการเก็บข้อมูล <br> 6. ระบบแสดงข้อมูลดาราศาสตร์จำลอง <br> 7. Astronomer ตรวจสอบข้อมูลจำลอง <br> 8. Astronomer สิ้นสุดเซสชั่นการสังเกตการณ์เสมือน <br> 9. ระบบบันทึกการตั้งค่าการสังเกตการณ์และผลการจำลอง |
| **Subflows**             | **S-1: การเลือกเป้าหมาย** <br> 1. Astronomer ค้นหารายการดาว (Catalog) <br> 2. ระบบแสดงวัตถุท้องฟ้าที่ตรงกับเงื่อนไข <br> 3. Astronomer เลือกวัตถุเป้าหมายที่ต้องการ (Target Object) <br> **S-2: การกำหนดค่าพารามิเตอร์** <br> 1. ระบบแสดงค่าต่างๆ ที่สามารถกำหนดได้ (Parameters) <br> 2. Astronomer ป้อนหรือปรับค่าการสังเกตการณ์ <br> 3. ระบบตรวจสอบความถูกต้องของค่าที่กำหนด (Validation) <br> 4. ระบบนำค่าที่ได้รับการยืนยันไปใช้ <br> **S-3: การตรวจสอบข้อมูล** <br> 1. ระบบแสดงข้อมูลจำลอง (Simulated Data) <br> 2. Astronomer สามารถดูรายละเอียดของข้อมูล (เช่น Spectrum, Image) <br> 3. Astronomer สามารถบันทึกมุมมองข้อมูลเฉพาะเพื่อใช้อ้างอิงในภายหลัง (Data View) |
| **Alternate/Exceptional Flow** | - หากพารามิเตอร์ที่ป้อนไม่ถูกต้อง ระบบจะแสดงข้อความแจ้งเตือนและให้นักดาราศาสตร์ปรับแก้ <br> - หากระบบมีทรัพยากรไม่เพียงพอสำหรับการจำลอง ระบบจะแจ้งเตือนให้นักดาราศาสตร์ลดความซับซ้อนของการจำลอง <br> - หากเกิดการขัดข้องของการเชื่อมต่อ ระบบจะบันทึกสถานะปัจจุบันและแจ้งให้นักดาราศาสตร์ทราบ เมื่อเชื่อมต่อได้อีกครั้งจะสามารถดำเนินการต่อได้ |

![ITDS361_All Diagram - 2  Operate the interactive observing-1](https://github.com/user-attachments/assets/b3c0d2ca-951b-429c-9d2c-18cb5dbd8cb8)
![ITDS361_All Diagram - 2  Operate the interactive observing](https://github.com/user-attachments/assets/5928e794-9c0a-49ef-90e7-1f0b45f178ce)
-----
| **Element**              | **Description** |
|--------------------------|-----------------|
| **Use Case Name**        | Manage astronomical data |
| **ID**                   | 03 |
| **Importance Level**     | High |
| **Primary Actor**        | Astronomer |
| **Use Case Type**        | Detail |
| **Stakeholders and Interests** | - **Astronomer**: ต้องการข้อมูลที่มีความถูกต้อง <br> - **Science Observer**: ต้องการจัดการดูแลข้อมูลของ Astronomer |
| **Brief Description**    | เป็น use case ที่อธิบายขั้นตอนการจัดการข้อมูล Astronomer |
| **Trigger**              | Science Observer ต้องการที่จะเปลี่ยนแปลงข้อมูล Astronomer |
| **Type**                 | External |
| **Relationships**        | **Association**: Astronomer, Science Observer |
| **Normal Flow of Events**| 1. Science Observer ยื่นการขอแก้ไขข้อมูลในระบบ <br> 2. Science Observer ลงชื่อเข้าใช้ระบบ (Username, Password) <br> 3. ระบบตรวจสอบความถูกต้องของผู้ใช้ <br> 4. ระบบดึงข้อมูลของ Astronomer ที่จัดเก็บไว้และแสดงรายการ <br> 5. ผู้ใช้สามารถเลือกที่จะทำตามขั้นตอนต่างๆได้ตาม (S-1, S-2, S-3) <br> 6. ระบบจะทำการบันทึกกระบวนที่เกิดขึ้นแล้วอัพเดทในฐานข้อมูล |
| **Subflows**             | **S-1: แก้ไขข้อมูล** <br> 1. เลือกข้อมูลที่จะทำการแก้ไข <br> 2. แก้ไขข้อมูล <br> 3. บันทึกข้อมูล <br> **S-2: ลบข้อมูล** <br> 1. เลือกข้อมูลที่จะทำการลบ <br> 2. ลบข้อมูล <br> 3. บันทึกข้อมูล <br> **S-3: กำหนดหมวดหมู่หรือจัดกลุ่มข้อมูล** <br> 1. เลือกข้อมูล <br> 2. สร้างประเภท(หากยังไม่มี) <br> 3. กำหนดหัวข้อหรือประเภทข้อมูล |
| **Alternate/Exceptional Flow** | - ไม่มีข้อมูลที่เลือกอยู่บนระบบ <br> - ข้อมูลของผู้ใช้เข้าสู่ระบบไม่ถูกต้องหรือไม่มีอยู่ |

![ITDS361_All Diagra - 3  Manage astronomical data – Science Observer](https://github.com/user-attachments/assets/c2f68f7b-129a-478f-883b-94edeb384224)
![ITDS361_All Diagram - 3  Manage astronomical data – Science Observer-2](https://github.com/user-attachments/assets/55c3d30a-4e70-43b9-82e0-7a1fde5ab4ab)
-----
| **Element**              | **Description** |
|--------------------------|-----------------|
| **Use Case Name**        | Control the telescope via the command line |
| **ID**                   | 04 |
| **Importance Level**     | High |
| **Primary Actor**        | Telescope Operator |
| **Use Case Type**        | Detail, Essential |
| **Stakeholders and Interests** | - **Telescope Operator**: ต้องสามารถควบคุมกล้องโทรทรรศน์ได้ผ่าน command line ได้อย่างปลอดภัยและมีประสิทธิภาพ <br> - **Science Observer**: ต้องสามารถควบคุมกล้องโทรทรรศน์ทาง observing program ได้ <br> - **Administrator**: ต้องการให้ระบบสามารถรักษาความปลอดภัยและการเข้าถึงของผู้ใช้ตาม access mode และ operational level ที่กำหนด <br> - **Astronomer**: ต้องสามารถเข้าดู live view ของกล้องโทรทรรศน์ได้ |
| **Brief Description**    | เป็น use case ที่อธิบายการควบคุมกล้องโทรทรรศน์ผ่าน command line |
| **Trigger**              | Telescope Operator ต้องการสั่งการเริ่มต้นหรือเปลี่ยนสถานะกล้องโทรทรรศน์ |
| **Type**                 | External |
| **Relationships**        | **Association**: Telescope operator |
| **Normal Flow of Events**| 1. Telescope Operator เปิด Command Line Interface (CLI) ของระบบควบคุมกล้อง <br> 2. Telescope Operator ลงชื่อเข้าใช้ (Username, Password) บน CLI <br> 3. ระบบตรวจสอบความถูกต้องของผู้ใช้ <br> 4. ระบบเข้าสู่หน้า Command Line Interface (CLI) <br> 5. Telescope Operator ป้อนคำสั่งควบคุมกล้องตามขั้นตอน (S-1, S-2, S-3, S-4) <br> 6. ระบบดำเนินการตามคำสั่ง <br> 7. ระบบแจ้งผลลัพธ์ของคำสั่งบน CLI ให้ Telescope Operator <br> 8. Telescope Operator สามารถพิมพ์คำสั่งเพิ่มเติมหรือออกจาก CLI ได้ |
| **Subflows**             | **S-1: เปิด/ปิดระบบ** <br> 1. ป้อนคำสั่งเพื่อเปิด (start telescope) หรือปิดระบบ (shutdown telescope) <br> 2. ระบบบันทึกผลคำสั่ง <br> 3. ระบบประมวลผลการเปิดหรือปิดกล้องโทรทรรศน์ <br> **S-2: ตรวจเช็คสถานะกล้อง** <br> 1. ป้อนคำสั่งตรวจสอบสถานะ (check status) <br> 2. ระบบบันทึกผลคำสั่ง <br> 3. ระบบดึงข้อมูลปัจจุบันของกล้อง เช่น ตำแหน่ง และสถานะการทำงาน <br> **S-3: รีเซ็ตระบบ** <br> 1. ป้อนคำสั่งรีเซ็ต (reset telescope) <br> 2. ระบบบันทึกผลคำสั่ง <br> 3. ระบบแจ้งเตือนว่าอาจมีการหยุดชั่วคราวระหว่างรีเซ็ต และขอให้ยืนยัน <br> **S-4: ปรับตำแหน่งกล้อง** <br> 1. ป้อนคำสั่งเปลี่ยนตำแหน่ง (set angle) <br> 2. ระบบบันทึกผลคำสั่ง <br> 3. ระบบส่งคำสั่งไปยังมอเตอร์ควบคุมกล้องเพื่อปรับตำแหน่ง |
| **Alternate/Exceptional Flow** | - หากผู้ใช้ไม่มีสิทธิ์ ระบบจะแสดงข้อความแจ้งเตือนและบันทึกเหตุการณ์ <br> - หากคำสั่งที่ป้อนผิดพลาด ระบบจะแจ้งเตือนและให้โอกาสผู้ใช้แก้ไข <br> - หากระบบไม่สามารถดำเนินการคำสั่งได้ อาจเกิดจากปัญหาทางเทคนิค ระบบจะแจ้งเตือน Telescope Operator |

![ITDS361_All Diagram - 4  Control the telescope via the command line-1](https://github.com/user-attachments/assets/a24c1015-2e28-4286-9f9f-b02e88c07bd6)
![ITDS361_All Diagram - 4  Control the telescope via the command line-2](https://github.com/user-attachments/assets/aa54604d-3b88-4a7e-8744-6c61e2bc7e87)
-----
| **Element**              | **Description** |
|--------------------------|-----------------|
| **Use Case Name**        | Access collected astronomical data |
| **ID**                   | 05 |
| **Importance Level**     | High |
| **Primary Actor**        | Astronomer |
| **Use Case Type**        | Detail |
| **Stakeholders and Interests** | - **Astronomer**: ต้องการเข้าถึงข้อมูลดาราศาสตร์ที่ถูกรวบรวมไว้ <br> - **Science Observer**: ตรวจสอบความถูกต้องของข้อมูลที่รวบรวมมา <br> - **Administrator**: จัดการสิทธิ์การเข้าถึงข้อมูลของนักดาราศาสตร์ |
| **Brief Description**    | เป็น use case การเข้าถึงข้อมูลดาราศาสตร์สำหรับ Astronomer |
| **Trigger**              | Astronomer ต้องการเข้าถึงข้อมูลที่เก็บรวบรวมไว้ |
| **Type**                 | External |
| **Relationships**        | **Association**: Astronomer |
| **Normal Flow of Events**| 1. Astronomer เข้าสู่ระบบ <br> 2. ระบบตรวจสอบความถูกต้องของผู้ใช้ <br> 3. Astronomer เลือกฟังก์ชันเข้าถึงข้อมูล <br> 4. Astronomer ระบุเงื่อนไขในการเข้าถึงข้อมูล (S-1,S-2,S-3) <br> 5. ระบบแสดงข้อมูลตามที่ระบุ <br> 6. Astronomer เลือกข้อมูลที่ต้องการ <br> 7. ระบบแสดงข้อมูลที่ถูกเลือก <br> 8. Astronomer สามารถดาวน์โหลดข้อมูลในระบบ |
| **Subflows**             | **S-1: ค้นหาข้อมูลจากวันที่** <br> 1. ระบุข้อมูลวันที่ <br> 2. ระบบแสดงข้อมูล <br> **S-2: ค้นหาข้อมูลจากวัตถุที่สังเกต** <br> 1. ระบุข้อมูลวัตถุที่สังเกต <br> 2. ระบบแสดงข้อมูล <br> **S-3: ค้นหาข้อมูลจากกล้องโทรทรรศน์ที่ใช้** <br> 1. ระบุข้อมูลกล้องโทรทรรศน์ที่ใช้ <br> 2. ระบบแสดงข้อมูล |
| **Alternate/Exceptional Flow** | - ข้อมูลเข้าสู่ระบบไม่ถูกต้อง <br> - หากไม่พบข้อมูลตามที่ระบุเงื่อนไขการค้นหาไว้ ระบบจะให้ระบุเงื่อนไขการค้นหาใหม่ |

![ITDS361_All Diagram - 5  Access collected astronomical data-1](https://github.com/user-attachments/assets/54f68eef-3963-4411-a1a0-869809f9e29c)
![ITDS361_All Diagram - 5  Access collected astronomical data-2](https://github.com/user-attachments/assets/8a840349-5f3f-47e7-b025-4509b646f94f)
-----
## Class Diagram
![ITDS361_All Diagram - Class Diagram](https://github.com/user-attachments/assets/ee0ff8d4-8541-42b0-bcd6-2dc433f78ae1)
