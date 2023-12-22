package com.aqara.common.aes;

public class AesException extends Exception {

	public final static int OK = 0;
	public final static int ValidateSignatureError = -40001;
	public final static int ParseJsonError = -40002;
	public final static int ComputeSignatureError = -40003;
	public final static int IllegalAesKey = -40004;
	public final static int ValidateCorpidError = -40005;
	public final static int EncryptAESError = -40006;
	public final static int DecryptAESError = -40007;
	public final static int IllegalBuffer = -40008;
	public final static int EncodeBase64Error = -40009;
	public final static int DecodeBase64Error = -40010;
	public final static int GenReturnJsonError = -40011;
	public static int ParseXmlError;

	private int code;

	private static String getMessage(int code) {
		switch (code) {
		case ValidateSignatureError:
			return "sign error";
		case ParseJsonError:
			return "json解析失败";
		case ComputeSignatureError:
			return "sha error";
		case IllegalAesKey:
			return "SymmetricKey error";
		case ValidateCorpidError:
			return "corpId xxxm1";
		case EncryptAESError:
			return "aes error";
		case DecryptAESError:
			return "aes error - 1";
		case IllegalBuffer:
			return "解密后得到的buffer非法";
		case EncodeBase64Error:
			return "base64加密错误";
		case DecodeBase64Error:
			return "base64解密错误";
		case GenReturnJsonError:
			return "json生成失败";
		default:
			return null; // cannot be
		}
	}

	public int getCode() {
		return code;
	}

	AesException(int code) {
		super(getMessage(code));
		this.code = code;
	}

}
