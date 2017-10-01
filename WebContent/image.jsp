<%@ page contentType="image/jpeg"
	import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*"
	pageEncoding="UTF-8"%><%!Color getRandColor(int fc, int bc,Random random) {//��Χ��������ɫ
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}%>
<%
	// ���ڴ��д���ͼ��
	int width = 60, height = 20;
	BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);
	//  ��ȡͼ��������
	Graphics g = image.getGraphics();
	//��������
	Random random = new Random();
	// �趨����ɫ
	g.setColor(getRandColor(230, 250,random));
	g.fillRect(0, 0, width, height);
	//�趨����
	g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	//���߿�
	g.setColor(new Color(0, 0, 0));
	g.drawRect(0, 0, width - 1, height - 1);

	// ������100������ߣ�ʹͼ���е���֤�벻�ױ��������̽�⵽
	for (int i = 0; i < 50; i++) {
		g.setColor(getRandColor(100, 250,random));
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(24);
		int yl = random.nextInt(24);
		g.drawLine(x, y, x + xl, y + yl);
	}
	// ȡ���������֤��(4λ����)
	String sRand = "";
	for (int i = 0; i < 4; i++) {
		String rand = String.valueOf(random.nextInt(10));
		sRand += rand;
		// ����֤����ʾ��ͼ����
		g.setColor(new Color(20 + random.nextInt(110), 20 + random
		.nextInt(110), 20 + random.nextInt(110))); //���ú����4����ɫ��ͬ����������Ϊ����̫�ӽ�����ֻ��ֱ�����
		g.drawString(rand, 13 * i + 4 + random.nextInt(4), 14 + random
		.nextInt(4));
	}
	// ����֤�����SESSION
	session.setAttribute("checkCode", sRand);

	// ͼ����Ч
	g.dispose();
	//����ҳ�治����
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	// ���ͼ��ҳ��
	java.io.OutputStream os=response.getOutputStream();	
	ImageIO.write(image, "JPEG", os);
	out.clear();
	out = pageContext.pushBody();
	
%>
