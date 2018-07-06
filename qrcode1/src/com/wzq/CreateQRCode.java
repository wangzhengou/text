package com.wzq;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;
public class CreateQRCode {
	public static void main(String[] args) throws IOException{
    	int version=15;
    	int imgSize=67+(version-1)*12;
    	Qrcode qrcode = new Qrcode();
    	qrcode.setQrcodeVersion(version);
    	qrcode.setQrcodeErrorCorrect('M');
    	qrcode.setQrcodeEncodeMode('B');
        BufferedImage bufferedImage = new BufferedImage (imgSize,imgSize,BufferedImage.TYPE_INT_RGB);
        Graphics2D gs=bufferedImage .createGraphics();
        gs.setBackground(Color.WHITE);
        gs.setColor(Color.BLACK);
        gs.clearRect(0, 0, imgSize, imgSize);
        String content="BEGIN:VCARD\r\n" + 
		               "FN:����:Wangzhenqian\r\n"+
		               "ORG:��˾:��ʦ	����:ѧ����\r\n"+
		               "TITLE:ѧ��\r\n" + 
		               "TEL;WORK;VOICE:56865654644\r\n"+
		               "TEL;HOME;VOICE:35256878542\r\n"+
		               "TEL;CELL;VOICE:54686468646\r\n"+
		               "ADR;WORK:�ػʵ�\r\n"+
	            	   "ADR;HOME:������ \r\n"+
		               "URL:https://www.baidu.com/"+
		               "EMAIL;HOME:857423133@qq.com\r\n" + 
		               "PHOTO;VALUE=uri:http://www.dijiaxueshe.com/images/logo.jpg\r\n" + 
		                "END:VCARD";

        boolean[][] calQrcode=qrcode.calQrcode(content.getBytes());
        String startRgb = "255,0,0";
        String endRgb = "0,0,255";
        int startR=0,startG=0,startB=0;
		if(null!= startRgb){
        	String[] rgb=startRgb.split(",");
        	startR=Integer.valueOf(rgb[0]);
        	startG=Integer.valueOf(rgb[1]);
        	startB=Integer.valueOf(rgb[2]);
		}
	   int endR=0,endG=0,endB=0;
			if(null!=endRgb){
	        	String[] rgb=endRgb.split(",");
	        	endR=Integer.valueOf(rgb[0]);
	        	endG=Integer.valueOf(rgb[1]);
	        	endB=Integer.valueOf(rgb[2]);
			}
        for(int i=0;i<calQrcode.length;i++){
        	for(int j=0;j<calQrcode[i].length;j++){
        		if(calQrcode[i][j]){
        			int r=startR+(endR-startR)*(i+1)/calQrcode.length;
        		    int g=startG+(endG-startG)*(i+1)/calQrcode.length;
        		    int b=startB+(endB-startB)*(i+1)/calQrcode.length;
        		    Color color = new  Color(r,g,b);
        		    gs.setColor(color);
        		    gs.fillRect(i*3, j*3, 3, 3);
        		}
        	}
        		
        }
    	//���ͷ��
		BufferedImage logo=ImageIO.read(new File("D:/logo.jpg"));
		//ͷ���С
		int logoSize=imgSize/3;
		//ͷ�����ʼλ��
		int o = (imgSize-logoSize)/2;
		//����ά���ϻ�ͼ��
		gs.drawImage(logo,o,o,logoSize,logoSize,null);
		
		gs.dispose();// �رջ滭����
		bufferedImage.flush();// �ѻ�����ͼƬ������ڳ䵱��
		try {
			ImageIO.write(bufferedImage, "png", new File("D:/logo.png"));// ���ڴ浱�е�ͼƬ�����Ӳ�̵���
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ok");
        
    }
    
}